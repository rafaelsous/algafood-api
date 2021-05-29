package com.rafaelsousa.algafood;

import com.rafaelsousa.algafood.domain.model.Cozinha;
import com.rafaelsousa.algafood.domain.repository.CozinhaRepository;
import com.rafaelsousa.algafood.util.DatabaseCleaner;
import com.rafaelsousa.algafood.util.ResourcesUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-dev.yml")
public class CadastroCozinhaIT {

    private static final int COZINHA_ID_INEXISTENTE = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private Cozinha cozinhaChinesa;

    private int quantidadeCozinhasCadastradas;

    private String jsonCozinhaGaucha;

    @BeforeEach
    public void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        basePath = "/cozinhas";

        databaseCleaner.clearTables();
        prepararDados();

        jsonCozinhaGaucha = ResourcesUtil.getContentFromResource("/json/cozinhaGaucha.json");
    }

    @Test
    public void testarRetorno200QuandoConsultarCozinhas() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testarRetornoEQuantidadeDeResultadosRetornadosQuandoConsultarCozinhas() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", hasSize(quantidadeCozinhasCadastradas));
    }

    @Test
    public void testarCadastrarNovaCozinha() {
        given()
                .body(jsonCozinhaGaucha)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testarRespostaEStatusAoConsultarCozinhaExistentePorId() {
        given()
                .pathParam("cozinhaId", cozinhaChinesa.getId())
                .accept(ContentType.JSON)
            .when()
                .get("/{cozinhaId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(cozinhaChinesa.getNome()));
    }

    @Test
    public void testarRespostaComStatus404AoConsultarCozinhaInexistente() {
        given()
                .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
                .accept(ContentType.JSON)
            .when()
                .get("/{cozinhaId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);

        cozinhaChinesa = new Cozinha();
        cozinhaChinesa.setNome("Chinesa");
        cozinhaRepository.save(cozinhaChinesa);

        quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
    }
}

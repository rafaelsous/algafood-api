package com.rafaelsousa.algafood;

import com.rafaelsousa.algafood.domain.model.Cozinha;
import com.rafaelsousa.algafood.domain.model.Restaurante;
import com.rafaelsousa.algafood.domain.repository.CozinhaRepository;
import com.rafaelsousa.algafood.domain.repository.RestauranteRepository;
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

import java.math.BigDecimal;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-dev.yml")
public class CadastroRestauranteIT {

    private static final String VIOLACAO_REGRA_DE_NEGOGIO = "Violação de regra de negócio";
    private static final String DADOS_INVALIDOS = "Dados inválidos";
    private static final int RESTAURANTE_ID_INEXISTENTE = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private String jsonResturanteNewYork;
    private String jsonResturanteNewYorkComCozinhaInexistente;
    private String jsonResturanteNewYorkSemCozinha;
    private String jsonResturanteNewYorkSemFrete;

    private Restaurante restauranteMammaMia;

    @BeforeEach
    public void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        basePath = "/restaurantes";

        jsonResturanteNewYork = ResourcesUtil.getContentFromResource("/json/restauranteNewYork.json");
        jsonResturanteNewYorkComCozinhaInexistente = ResourcesUtil.getContentFromResource("/json/restauranteNewYorkComCozinhaInexistente.json");
        jsonResturanteNewYorkSemCozinha = ResourcesUtil.getContentFromResource("/json/restauranteNewYorkSemCozinha.json");
        jsonResturanteNewYorkSemFrete = ResourcesUtil.getContentFromResource("/json/restauranteNewYorkSemFrete.json");

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void testarRetorno200QuandoConsultarRestrauntes() {
        given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testarStatus201AoCadastrarNovoRestaurante() {
        given()
                .body(jsonResturanteNewYork)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testarStatus400QuandoCadastrarRestauranteSemFrete() {
        given()
                .body(jsonResturanteNewYorkSemFrete)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(DADOS_INVALIDOS));
    }

    @Test
    public void testarStatus400QuandoCadastrarRestauranteSemCozinha() {
        given()
                .body(jsonResturanteNewYorkSemCozinha)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(DADOS_INVALIDOS));
    }

    @Test
    public void testarStatus400QuandoCadastrarRestauranteComCozinhaInexistente() {
        given()
                .body(jsonResturanteNewYorkComCozinhaInexistente)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(VIOLACAO_REGRA_DE_NEGOGIO));
    }

    @Test
    public void testarRespostaEStatusAoConsultarRestauranteExistentePorId() {
        given()
                .pathParam("cozinhaId", restauranteMammaMia.getId())
                .accept(ContentType.JSON)
            .when()
                .get("/{cozinhaId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(restauranteMammaMia.getNome()));
    }

    @Test
    public void testarRespostaComStatus404AoConsultarRestauranteInexistente() {
        given()
                .pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
                .accept(ContentType.JSON)
            .when()
                .get("/{restauranteId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        Cozinha cozinhaBrasileira = new Cozinha();
        cozinhaBrasileira.setNome("Brasileira");
        cozinhaRepository.save(cozinhaBrasileira);

        Cozinha cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cozinhaRepository.save(cozinhaAmericana);

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Nuestra Casa");
        restaurante1.setAberto(Boolean.TRUE);
        restaurante1.setAtivo(Boolean.TRUE);
        restaurante1.setTaxaFrete(new BigDecimal(10.99));
        restaurante1.setCozinha(cozinhaBrasileira);
        restauranteRepository.save(restaurante1);

        restauranteMammaMia = new Restaurante();
        restauranteMammaMia.setNome("Mamma Mia");
        restauranteMammaMia.setAberto(Boolean.TRUE);
        restauranteMammaMia.setAtivo(Boolean.TRUE);
        restauranteMammaMia.setTaxaFrete(new BigDecimal(7.89));
        restauranteMammaMia.setCozinha(cozinhaAmericana);
        restauranteRepository.save(restauranteMammaMia);
    }
}

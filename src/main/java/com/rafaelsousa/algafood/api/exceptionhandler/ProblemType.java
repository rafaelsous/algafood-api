package com.rafaelsousa.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada"),
    ENTIDADE_ME_USO("Entidade em uso", "/entidade-em-uso"),
    REGRA_NEGOCIO("Violação de regra de negócio", "/regra-negocio"),
    MENSAGEM_ILEGIVEL("Mensagem ilegível", "/mensagem-ilegivel"),
    FORMATO_INVALIDO("Formato inválido", "/formato-invalido"),
    PARAMETRO_INVALIDO("Parâmetro inválido", "/parametro-invalido");

    private String title;
    private String uri;

    ProblemType(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }
}

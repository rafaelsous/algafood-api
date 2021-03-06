package com.rafaelsousa.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado"),
    ENTIDADE_ME_USO("Entidade em uso", "/entidade-em-uso"),
    REGRA_NEGOCIO("Violação de regra de negócio", "/regra-negocio"),
    MENSAGEM_ILEGIVEL("Mensagem ilegível", "/mensagem-ilegivel"),
    FORMATO_INVALIDO("Formato inválido", "/formato-invalido"),
    PARAMETRO_INVALIDO("Parâmetro inválido", "/parametro-invalido"),
    ERRO_NO_SISTEMA("Erro interno no servidor", "/erro-interno-servidor"),
    DADOS_INVALIDOS("Dados inválidos", "/dados-invalidos");

    private String title;
    private String uri;

    ProblemType(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }
}

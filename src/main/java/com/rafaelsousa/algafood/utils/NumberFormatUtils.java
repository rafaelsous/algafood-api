package com.rafaelsousa.algafood.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NumberFormatUtils {

    private static final String FORMATO_DATA_HORA = "dd/MM/yyyy HH:mm:ss";
    private static final String FORMATO_MOEDA = "'R$ '#,###,##0.00";

    public static String dataFormatada(LocalDateTime dataHora) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern(FORMATO_DATA_HORA);
        return dataHora.format(formatador);
    }

    public static String valorMonetarioFormatado(BigDecimal valor) {
        DecimalFormat formatador = new DecimalFormat(FORMATO_MOEDA);
        return formatador.format(valor);
    }

}

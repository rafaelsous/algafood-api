package com.rafaelsousa.algafood.core.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Objects;

public class RestauranteTaxaZeroValidator implements ConstraintValidator<RestauranteTaxaZero, Object> {

    private String fieldValue;
    private String fieldDescription;
    private String required;

    @Override
    public void initialize(RestauranteTaxaZero constraint) {
        this.fieldValue = constraint.fieldValue();
        this.fieldDescription = constraint.fieldDescription();
        this.required = constraint.required();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean valido = true;

        try {
            BigDecimal valor = (BigDecimal) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(value.getClass(), fieldValue)).getReadMethod().invoke(value);
            String descricao = (String) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(value.getClass(), fieldDescription)).getReadMethod().invoke(value);

            if (Objects.nonNull(valor) && BigDecimal.ZERO.compareTo(valor) == 0 && StringUtils.isNotBlank(descricao)) {
                valido = descricao.toLowerCase().contains(required.toLowerCase());
            }
        } catch (Exception ex) {
            throw new ValidationException(ex);
        }

        return valido;
    }
}

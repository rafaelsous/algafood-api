package com.rafaelsousa.algafood.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { RestauranteTaxaZeroValidator.class })
@Documented
public @interface RestauranteTaxaZero {

    String message() default "descrição obrigatória não está presente";

    Class<?> [] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String fieldValue();

    String fieldDescription();

    String required();

}

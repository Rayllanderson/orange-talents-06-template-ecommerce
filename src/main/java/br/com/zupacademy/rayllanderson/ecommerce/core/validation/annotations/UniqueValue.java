package br.com.zupacademy.rayllanderson.ecommerce.core.validation.annotations;

import br.com.zupacademy.rayllanderson.ecommerce.core.validation.validators.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Verifica se o campo é único ao tentar persistir.
 * Deve ser usada somente no contexto do Spring.
 */
@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

    String message() default "Campo deve ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> domainClass();

    String field();
}

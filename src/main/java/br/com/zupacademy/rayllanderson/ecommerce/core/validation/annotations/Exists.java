package br.com.zupacademy.rayllanderson.ecommerce.core.validation.annotations;

import br.com.zupacademy.rayllanderson.ecommerce.core.validation.validators.ExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExistsValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Exists {

    String message() default "{Campo deve existir}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> domainClass();

    String field();
}

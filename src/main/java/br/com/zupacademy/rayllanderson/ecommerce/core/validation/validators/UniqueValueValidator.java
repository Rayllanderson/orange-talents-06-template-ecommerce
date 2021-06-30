package br.com.zupacademy.rayllanderson.ecommerce.core.validation.validators;

import br.com.zupacademy.rayllanderson.ecommerce.core.validation.annotations.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private Class<?> klass;
    private String field;

    @PersistenceContext
    private final EntityManager em;

    public UniqueValueValidator(EntityManager em) {
        this.em = em;
    }

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        klass = constraintAnnotation.domainClass();
        field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object == null || object.equals("")) return false;
        String jpql = "SELECT 1 FROM " + klass.getName() + " WHERE " + field + " = :field";
        List<?> result = em.createQuery(jpql).setParameter("field", object).getResultList();
        Assert.state(result.size() <= 1, "Existem mais de um(a) " + klass.getSimpleName() + " com o atributo " + field);
        return result.isEmpty();
    }
}

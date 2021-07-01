package br.com.zupacademy.rayllanderson.ecommerce.core.validation.validators;

import br.com.zupacademy.rayllanderson.ecommerce.core.validation.annotations.Exists;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsValidator implements ConstraintValidator<Exists, Object> {

    private Class<?> klass;
    private String field;

    @PersistenceContext
    private final EntityManager em;

    public ExistsValidator(EntityManager em) {
        this.em = em;
    }

    @Override
    public void initialize(Exists constraintAnnotation) {
        klass = constraintAnnotation.domainClass();
        field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        String jpql = "SELECT 1 FROM " + klass.getName() + " WHERE " + field + " = :field";
        List<?> result = em.createQuery(jpql).setParameter("field", object).getResultList();
        Assert.state(result.size() > 0, "Não existe nenhum(a) " + klass.getSimpleName() + " com " + field + ": " + object);
        return true;
    }
}

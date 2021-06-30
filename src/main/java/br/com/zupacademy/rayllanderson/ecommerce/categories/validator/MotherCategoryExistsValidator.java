package br.com.zupacademy.rayllanderson.ecommerce.categories.validator;

import br.com.zupacademy.rayllanderson.ecommerce.categories.requests.CategoryPostRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class MotherCategoryExistsValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(CategoryPostRequest.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryPostRequest request = (CategoryPostRequest) o;
        if(errors.hasErrors() || request.getMotherCategoryId() == null) return;

        String jpql = "SELECT  COUNT(*) FROM Category c WHERE c.id = " + request.getMotherCategoryId();
        long count = (long) manager.createQuery(jpql).getSingleResult();

        if(count <= 0){
            errors.reject("motherId", null, "A categoria Mãe que você está tentando inserir, não existe.");
        }
    }
}

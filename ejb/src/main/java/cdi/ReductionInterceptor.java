package cdi;

import model.Category;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.Optional;

@Reductional
@Interceptor
public class ReductionInterceptor {

    @PersistenceContext(name = "com.irmikrys.soa")
    private EntityManager em;

    @AroundInvoke
    public Object manageCreation(InvocationContext invocationContext) throws Exception {

        //idCategory, name, fortune, property, power
        Object[] contextParams = invocationContext.getParameters();
        Integer idCategory = (Integer) contextParams[0];
        System.out.println(Arrays.asList(contextParams));

        TypedQuery<Category> categoryQuery = em.createQuery(
                "SELECT c FROM Category c " +
                        "JOIN FETCH c.user u " +
                        "JOIN FETCH u.typeSet t " +
                        "WHERE c.idCategory = :idCategory", Category.class
        );
        categoryQuery.setParameter("idCategory", idCategory);
        Integer idTypeSet = categoryQuery.getSingleResult().getUser().getTypeSet().getIdTypeSet();

        TypedQuery<Integer> query = em.createQuery(
          "SELECT max(e.fortune) FROM TypeSet t " +
                  "JOIN t.users u " +
                  "JOIN u.categories c " +
                  "JOIN c.elements e " +
                  "WHERE t.idTypeSet = :idTypeSet", Integer.class
        );
        query.setParameter("idTypeSet", idTypeSet);

        Integer amountToCheck = Integer.valueOf(contextParams[2].toString());
        Optional<Integer> maxAmount = Optional.ofNullable(query.getSingleResult());

        maxAmount.ifPresent(max -> {
            if(amountToCheck > max) contextParams[2] = max;
        });
        System.out.println(Arrays.asList(contextParams));

        return invocationContext.proceed();
    }
}

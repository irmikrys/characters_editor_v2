package dao;

import model.Category;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SecurityDomain("soaEJBApplicationDomain")
@PermitAll
@Stateless
public class CategoryDAO {

    @PersistenceContext(unitName = "com.irmikrys.soa")
    private EntityManager em;

    public Optional<Category> findById(Integer idCategory) {
        Optional<Category> category;
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c " +
                        "JOIN FETCH c.elements e " +
                        "WHERE c.idCategory = :idCategory", Category.class
        );
        query.setParameter("idCategory", idCategory);
        try {
            category = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            category = Optional.empty();
        }
        return category;
    }

    public List<Category> findAll() {
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c " +
                        "JOIN FETCH c.elements e ", Category.class);
        return new ArrayList<>(new HashSet<>(query.getResultList()));
    }

    public void add(Category category) {
        em.persist(category);
    }

}

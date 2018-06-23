package dao;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RolesAllowed({ "User" })
public abstract class AbstractDAO<T, ID> {

    protected Class<T> entityClass;

    @PersistenceContext(unitName = "com.irmikrys.soa")
    protected EntityManager em;

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }

    public List<T> findAll() {
        CriteriaQuery<T> criteria = em.getCriteriaBuilder().createQuery(entityClass);
        criteria.from(entityClass);

        return em.createQuery(criteria).getResultList();
    }

    public void add(T entity) {
        em.persist(entity);
    }

    public void remove(ID id) {
        findById(id).ifPresent(e -> {
            em.remove(e);
        });
    }
}

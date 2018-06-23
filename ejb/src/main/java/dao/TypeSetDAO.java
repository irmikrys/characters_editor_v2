package dao;

import model.TypeSet;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
public class TypeSetDAO extends AbstractDAO<TypeSet, Integer> {

    public TypeSetDAO() {
        this.entityClass = TypeSet.class;
    }

    public Optional<TypeSet> findTypeSetByCategoryId(Integer idCategory) {
        Optional<TypeSet> typeSet;
        TypedQuery<TypeSet> query = em.createQuery(
                "SELECT t FROM TypeSet t " +
                        "JOIN t.users u " +
                        "JOIN u.categories c " +
                        "WHERE c.idCategory = :idCategory", TypeSet.class
        );
        query.setParameter("idCategory", idCategory);
        try {
            typeSet = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            typeSet = Optional.empty();
        }
        return typeSet;
    }

    public Optional<TypeSet> findTypeSetByElementId(Integer idElement) {
        Optional<TypeSet> typeSet;
        TypedQuery<TypeSet> query = em.createQuery(
                "SELECT t FROM TypeSet t " +
                        "JOIN t.users u " +
                        "JOIN u.categories c " +
                        "JOIN c.elements e " +
                        "WHERE e.idElement = :idElement", TypeSet.class
        );
        query.setParameter("idElement", idElement);
        try {
            typeSet = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            typeSet = Optional.empty();
        }
        return typeSet;
    }

}

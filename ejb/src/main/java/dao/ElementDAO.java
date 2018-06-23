package dao;

import model.Category;
import model.Element;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
public class ElementDAO extends AbstractDAO<Element, Integer> {

    public ElementDAO() {
        this.entityClass = Element.class;
    }

    public Optional<Element> findWithCategory(Integer id) {
        Optional<Element> element;
        TypedQuery<Element> query = em.createQuery(
                "SELECT e FROM Element e " +
                        "JOIN FETCH e.category c " +
                        "WHERE e.idElement = :id", Element.class
        );
        query.setParameter("id", id);
        try {
            element = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            element = Optional.empty();
        }
        return element;
    }

    public Optional<Element> findWithCategoryAndUser(Integer id) {
        Optional<Element> element;
        TypedQuery<Element> query = em.createQuery(
                "SELECT e FROM Element e " +
                        "JOIN FETCH e.category c " +
                        "JOIN FETCH c.user u " +
                        "WHERE e.idElement = :id", Element.class
        );
        query.setParameter("id", id);
        try {
            element = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            element = Optional.empty();
        }
        return element;
    }

    public List<Element> findAllByIdCategory(Integer idCategory) {
        TypedQuery<Element> query = em.createQuery(
                "SELECT e from Element e " +
                        "JOIN FETCH e.category c " +
                        "WHERE c.idCategory = :idCategory", Element.class
        );
        query.setParameter("idCategory", idCategory);
        return query.getResultList();
    }

    public List<Element> findBestElementsByTypeSet(Integer idTypeSet) {
        TypedQuery<Element> query = em.createQuery(
                "SELECT e from Element e " +
                        "JOIN FETCH e.category c " +
                        "JOIN FETCH c.user u " +
                        "JOIN FETCH u.typeSet t " +
                        "WHERE t.idTypeSet = :idTypeSet " +
                        "ORDER BY e.fortune DESC ", Element.class
        );
        query.setParameter("idTypeSet", idTypeSet);
        query.setMaxResults(5);
        return query.getResultList();
    }

    public void update(Category category, Integer id, String name, Integer fortune, Integer propType, Integer power) {
        findById(id).ifPresent(element -> {
            if (category != null)
                element.setCategory(category);
            if (name != null && !name.isEmpty())
                element.setName(name);
            if (fortune != null)
                element.setFortune(fortune);
            if (propType != null)
                element.setProperty(propType);
            if (power != null)
                element.setPower(power);
        });
    }

}

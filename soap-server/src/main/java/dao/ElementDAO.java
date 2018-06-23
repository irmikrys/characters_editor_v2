package dao;

import model.Element;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Stateless
public class ElementDAO {

    @PersistenceContext(unitName = "com.irmikrys.soa")
    private EntityManager em;

    public Optional<Element> findById(Integer idElement) {
        Optional<Element> element;
        TypedQuery<Element> query = em.createQuery(
                "SELECT e FROM Element e " +
                        "WHERE e.idElement = :idElement", Element.class);
        query.setParameter("idElement", idElement);
        try {
            element = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            element = Optional.empty();
        }
        return element;
    }

    public void update(Integer id, Integer fortune, Integer power) {
        findById(id).ifPresent(element -> {
            if (fortune != null)
                element.setFortune(fortune);
            if (power != null)
                element.setPower(power);
        });
    }

    public void add(Element element) {
        em.persist(element);
    }

}

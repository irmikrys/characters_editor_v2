package dao;

import model.Element;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SecurityDomain("soaEJBApplicationDomain")
@PermitAll
@Stateless
public class ElementDAO {

    @PersistenceContext(unitName = "com.irmikrys.soa")
    private EntityManager em;

    public void add(Element element) {
        em.persist(element);
    }

}

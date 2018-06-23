package dao;

import dto.CategoryDTO;
import model.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class CategoryDAO {

    @PersistenceContext(unitName = "com.irmikrys.soa")
    private EntityManager em;

    public Optional<Category> findById(Integer idCategory) {
        Optional<Category> category;
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c " +
                        "WHERE c.idCategory = :idCategory", Category.class);
        query.setParameter("idCategory", idCategory);
        try {
            category = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            category = Optional.empty();
        }
        return category;
    }

    public List<CategoryDTO> findAll() {
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c ", Category.class);
        return new HashSet<>(query.getResultList())
                .stream()
                .sorted(Comparator.comparing(Category::getIdCategory))
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> findAllByIdUser(Integer idUser) {
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c " +
                        "WHERE c.idUser = :idUser", Category.class);
        query.setParameter("idUser", idUser);
        return query.getResultList()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    public void add(Category category) {
        em.persist(category);
    }

}

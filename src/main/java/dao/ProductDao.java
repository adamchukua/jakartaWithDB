package dao;

import com.example.learnjdbc.CreateConnection;
import entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductDao {
    public void saveProduct(Product product) {
        EntityManager em = CreateConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public Product getProduct(Long id) {
        EntityManager em = CreateConnection.getEntityManager();
        return em.find(Product.class, id);
    }

    public List<Product> getAllProducts() {
        EntityManager em = CreateConnection.getEntityManager();
        TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
        return query.getResultList();
    }

    public void updateProduct(Product product) {
        EntityManager em = CreateConnection.getEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteProduct(Long id) {
        EntityManager em = CreateConnection.getEntityManager();
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.getTransaction().begin();
            em.remove(product);
            em.getTransaction().commit();
        }
        em.close();
    }
}
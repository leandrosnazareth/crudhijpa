package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Produto;

public class ProdutoDao {

    private static ProdutoDao instance;
    protected EntityManager entityManager;
           
    public static ProdutoDao getInstance(){
        if(instance == null){
            instance = new ProdutoDao();
        }
        return instance;
    }
    
    private ProdutoDao(){
        entityManager = getEntityManager();
    }
    
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProdutoFxPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }
    
    public Produto getById(final Long id) {
        return entityManager.find(Produto.class, id);
    }
   
    @SuppressWarnings("unchecked")
    public List<Produto> findAll() {
        return entityManager.createQuery("FROM " + Produto.class.getName()).getResultList();
    }
        
    public void deleteAll(){
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from product where id > 0").executeUpdate();
        entityManager.getTransaction().commit();
    }
        
   
    public void persist(Produto product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
   
    public void merge(Produto product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
   
    public void remove(Produto product) {
        try {
            entityManager.getTransaction().begin();
            product = entityManager.find(Produto.class, product.getId());
            entityManager.remove(product);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
   
    public void removeById(final Long id) {
        try {
            Produto product = getById(id);
            remove(product);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Produto getProduct(final Long id) {
        Produto product = null;
        try {
            product = getById(id);    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }
    
    public void update(Produto product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}

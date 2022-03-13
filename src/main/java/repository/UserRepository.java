package repository;
import  model.User;

import javax.persistence.EntityManager;
import java.util.List;


public class UserRepository{
    private EntityManager em;
    public UserRepository(EntityManager em){
        this.em=em;
    }
    public void addUser(User user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
    public User findByUsername(String username){
        return em.createNamedQuery("User.findByUsername",User.class).setParameter("username",username).getSingleResult();
    }
    public User findById(int id){
        return em.createNamedQuery("User.findById",User.class).setParameter("id",id).getSingleResult();
    }
    public List getUsers(){
        List users= em.createQuery("SELECT u from User u").getResultList();
        return users;
    }
}

package repository;

import model.Destination;

import javax.persistence.EntityManager;
import java.util.List;

public class DestinationRepository {
    private EntityManager em;
    public DestinationRepository(EntityManager em){
        this.em=em;
    }
    public void addDestination(Destination destination){
        em.getTransaction().begin();
        em.persist(destination);
        em.getTransaction().commit();
    }
    public Destination findByName(String name){
        return em.createNamedQuery("Destination.findByName",Destination.class).setParameter("name",name).getSingleResult();
    }

    public List getDestinaitons(){
        List destinations= em.createQuery("SELECT d from Destination d").getResultList();
        return destinations;
    }

    public void deleteDestination(Destination destination){
        em.getTransaction().begin();
        em.remove(destination);
        em.flush();
        em.clear();
        em.getTransaction().commit();
    }
}

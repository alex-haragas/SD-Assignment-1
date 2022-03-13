package repository;

import model.Vacation;

import javax.persistence.EntityManager;
import java.util.List;

public class VacationRepository {
    private EntityManager em;
    public VacationRepository(EntityManager em){
        this.em=em;
    }
    public void addVacation(Vacation vacation){
        em.getTransaction().begin();
        em.persist(vacation);
        em.getTransaction().commit();
    }
    public Vacation findByName(String name){
        return em.createNamedQuery("Vacation.findByName",Vacation.class).setParameter("name",name).getSingleResult();
    }

    public List getVacations(){
        List vacations= em.createQuery("SELECT v from Vacation v").getResultList();
        return vacations;
    }

    public void deleteVacation(Vacation v){
        em.getTransaction().begin();
        em.remove(v);
        em.flush();
        em.clear();
        em.getTransaction().commit();
    }
}
package service;


import model.User;
import model.Vacation;
import repository.VacationRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class VacationService {
    private VacationRepository vr;
    public VacationService(EntityManager em){
        this.vr=new VacationRepository(em);
    }
    public boolean addVacation(Vacation v){
        int c=0;
        try{
            vr.findByName(v.getName());
        }
        catch (Exception e){
            c++;
        }
        if(c!=1){
            return false;
        }
        vr.addVacation(v);
        return true;
    }
    public Vacation findByName(String name){
        try {
            return vr.findByName(name);
        }
        catch (Exception e){
            return null;
        }
    }
    public List<Vacation> getVacations(){
        return vr.getVacations();
    }

    public boolean deleteVacation(String name){
        try {
            Vacation v=vr.findByName(name);
            for(User u:v.getUsers()){
                v.deleteUser(u);
            }
            vr.deleteVacation(v);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
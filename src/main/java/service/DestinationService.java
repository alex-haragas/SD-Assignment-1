package service;

import controller.VacationController;
import model.Destination;
import model.Vacation;
import repository.DestinationRepository;
import repository.VacationRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DestinationService {
    private DestinationRepository dr;
    public DestinationService(EntityManager em){
        this.dr=new DestinationRepository(em);
    }
    public boolean addDestination(Destination d){
        int c=0;
        try{
            dr.findByName(d.getName());
        }
        catch (Exception e){
            c++;
        }
        if(c!=1){
            return false;
        }
        dr.addDestination(d);
        return true;
    }
    public Destination findByName(String name){
        try {
            return dr.findByName(name);
        }
        catch (Exception e){
            return null;
        }
    }
    public List<Destination> getDestinations(){
        return dr.getDestinaitons();
    }
    public boolean deleteDestination(String name,VacationController vc){
        try {
            Destination d=dr.findByName(name);
            dr.deleteDestination(d);
            List<Vacation> vacations=vc.getVacations();
            for(Vacation v:vacations){
                if(v.getDestination().equals(d)){
                    vc.deleteVacation(v.getName());
                }
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}

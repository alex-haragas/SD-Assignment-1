package controller;

import model.Destination;
import service.DestinationService;

import javax.persistence.EntityManager;
import java.util.List;

public class DestinationController {
    private DestinationService ds;
    public DestinationController(EntityManager em){
        ds=new DestinationService(em);
    }

    public boolean addDestination(String name){
        if(ds.findByName(name)==null){
            Destination d=new Destination(name);
            ds.addDestination(d);
            return true;
        }
        return false;
    }


    public Destination findDestination(String name){
        Destination fDestination=ds.findByName(name);
        if(fDestination==null){
            return null;
        }
        else
            return fDestination;
    }
    public List<Destination> getDestinations(){
        return ds.getDestinations();
    }

    public boolean deleteDestination(String name,VacationController vc){
        return ds.deleteDestination(name,vc);
    }
}

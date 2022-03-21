package controller;

import com.example.sa1.dateChecker;
import model.Destination;
import model.Vacation;
import service.VacationService;

import javax.persistence.EntityManager;
import java.util.List;

public class VacationController {
    private VacationService vs;
    public VacationController(EntityManager em){
        vs=new VacationService(em);
    }

    public Vacation addVacation(String name, Destination d,String extraDetails,String period,double price,String limit){
        if(vs.findByName(name)==null){
            dateChecker dc=new dateChecker();
            if(dc.compDate(period.substring(0,9),period.substring(11)) && price>0 && Integer.parseInt(limit)>0){
                Vacation v=new Vacation(name,limit,price,period,extraDetails,d);
                if(insertVacation(v))
                    return v;
                else
                    return null;
            }
            else
                return null;
        }
        return null;
    }

    public boolean insertVacation(Vacation v){
        if(vs.addVacation(v)){
            return true;
        }
        else{
            return false;
        }
    }
    public Vacation findVacation(String name){
        Vacation fVacation=vs.findByName(name);
        if(fVacation==null){
            return null;
        }
        else
            return fVacation;
    }
    public List<Vacation> getVacations(){
        return vs.getVacations();
    }

    public boolean deleteVacation(String name){
        return vs.deleteVacation(name );
    }
}
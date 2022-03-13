package service;

import model.User;
import repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class UserService {
    private UserRepository ur;
    public UserService(EntityManager em){
        this.ur=new UserRepository(em);
    }
    public boolean addUser(User u){
        int c=0;
        try{
            ur.findByUsername(u.getUsername());
        }
        catch (Exception e){
            c++;
        }
        if(c!=1){
            return false;
        }
        ur.addUser(u);
        return true;
    }
    public User findUserByUsername(String username){
        try {
            return ur.findByUsername(username);
        }
        catch (Exception e){
            return null;
        }
    }
    public List<User> getUsers(){
        return ur.getUsers();
    }

}

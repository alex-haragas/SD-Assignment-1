package controller;

import model.User;
import service.UserService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class UserController {
    private UserService us;
    public UserController(EntityManager em){
        us=new UserService(em);
    }
    public boolean insertUser(User u){
        if(us.addUser(u)){
            return true;
        }
        else{
            return false;
        }
    }
    public User findUser(String Username){
        User fUser=us.findUserByUsername(Username);
        if(fUser==null){
            return null;
        }
        else
            return fUser;
    }
    public List<User> getUsers(){
        return us.getUsers();
    }

}

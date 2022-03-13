package com.example.sa1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Destination;
import model.User;
import model.Vacation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        EntityManager em=entityManagerFactory.createEntityManager();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root=(Parent)fxmlLoader.load();
        HelloController controller=fxmlLoader.<HelloController>getController();
        controller.setEm(em);
        Scene scene = new Scene(root,800,850);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        stage.show();
    }


    public static final EntityManagerFactory entityManagerFactory=
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    public static void main(String[] args) {



        launch();
    }
}
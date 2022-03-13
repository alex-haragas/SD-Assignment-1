package com.example.sa1;

import controller.DestinationController;
import controller.UserController;
import controller.VacationController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Destination;
import model.User;
import model.Vacation;

import javax.persistence.EntityManager;
import java.util.List;

public class HelloController {

    @FXML
    private TextField usrField = new TextField();
    @FXML
    private PasswordField passField = new PasswordField();
    @FXML
    private PasswordField rePassField = new PasswordField();
    @FXML
    private Label usrLabel = new Label();
    @FXML
    private Label passLabel = new Label();
    @FXML
    private Label rePassLabel = new Label();
    @FXML
    private TableView vacationTable = new TableView();
    @FXML
    private TextField minPriceF = new TextField();
    @FXML
    private TextField maxPriceF = new TextField();
    @FXML
    private TextField destF = new TextField();
    @FXML
    private TextField startDateF = new TextField();
    @FXML
    private TextField endDateF = new TextField();
    @FXML
    private CheckBox bookedCheck = new CheckBox();
    @FXML
    private Label minPriceL = new Label();
    @FXML
    private Label maxPriceL = new Label();
    @FXML
    private Label destL = new Label();
    @FXML
    private Label startDateL = new Label();
    @FXML
    private Label endDateL = new Label();
    @FXML
    private TextField extraDetF=new TextField();
    @FXML
    private Label extraDetL=new Label();

    @FXML
    private Button loginB = new Button();
    @FXML
    private Button signupB = new Button();
    @FXML
    private Button searchB = new Button();
    @FXML
    private Button addB=new Button();
    @FXML
    private Button modifyB=new Button();
    @FXML
    private Button deleteB=new Button();
    @FXML
    private Button destB=new Button();
    @FXML
    private Button deleteDB=new Button();

    private EntityManager em;
    private UserController uc;
    private VacationController vc;
    private DestinationController dc;
    private User loggedUser;
    private List<Vacation> vacations;

    @FXML
    protected void login() {
        String username = usrField.getText();
        String pass = passField.getText();

        if (username.equals("") || pass.equals("")) {
            System.out.println("Missing info");
        } else {
            loggedUser = uc.findUser(username);
            if (loggedUser != null) {
                if (loggedUser.getPassword().equals(pass)) {
                    System.out.println("Worked");
                    if (loggedUser.getRole().equals("User")) {
                        this.userPage();
                    }
                    else{
                        this.agencyPage();
                    }
                } else {
                    showMessage("Error","Wrong password.");
                }
            } else {
                showMessage("Error","Wrong username.");
            }
        }
    }

    @FXML
    protected void signup() {
        String username = usrField.getText();
        String pass = passField.getText();
        String rePass = rePassField.getText();

        if (username.equals("") || pass.equals("") || rePass.equals("")) {
            showMessage("Error","Please fill all fields.");
        } else {
            if (pass.equals(rePass)) {
                loggedUser = new User(username, pass);
                if (!uc.insertUser(loggedUser)) {
                    showMessage("Error","Username already exists.");
                } else {
                    showMessage("Success","Created new account");
                    this.userPage();
                }
            } else {
                showMessage("Error","Passwords do not match.");
            }
        }

    }

    protected void userPage() {
        usrField.setVisible(false);
        passField.setVisible(false);
        rePassField.setVisible(false);
        usrLabel.setVisible(false);
        passLabel.setVisible(false);
        rePassLabel.setVisible(false);
        loginB.setVisible(false);
        signupB.setVisible(false);

        vacationTable.setVisible(true);
        searchB.setVisible(true);
        maxPriceF.setVisible(true);
        minPriceF.setVisible(true);
        destF.setVisible(true);
        startDateF.setVisible(true);
        endDateF.setVisible(true);
        bookedCheck.setVisible(true);
        vacationTable.setVisible(true);

        maxPriceL.setVisible(true);
        minPriceL.setVisible(true);
        destL.setVisible(true);
        startDateL.setVisible(true);
        endDateL.setVisible(true);

        maxPriceL.setText("Max Price");
        minPriceL.setText("Min Price");
        destL.setText("Destination");
        startDateL.setText("Starts Until");
        endDateL.setText("Ends Until");


        TableColumn nameCol = new TableColumn("Name");
        TableColumn destCol = new TableColumn("Destination");
        TableColumn descriptionCol = new TableColumn("Description");
        TableColumn priceCol = new TableColumn("Price");
        TableColumn periodCol = new TableColumn("Period");
        TableColumn limitCol = new TableColumn("Limit");

        vacationTable.getColumns().removeAll();

        nameCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("name")
        );
        destCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("destinationName")
        );
        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("extraDetails")
        );
        priceCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, Double>("price")
        );
        periodCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("period")
        );
        limitCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, Integer>("limitB")
        );

        vacationTable.getColumns().addAll(nameCol, destCol, descriptionCol, priceCol, periodCol, limitCol);

        vacations = vc.getVacations();
        for (Vacation v : vacations) {
            if (!v.getStatus().equals("BOOKED") || v.getUsers().contains(loggedUser)) {
                    vacationTable.getItems().add(v);
            }
        }
    }

    protected void agencyPage() {
        usrField.setVisible(false);
        passField.setVisible(false);
        rePassField.setVisible(false);
        usrLabel.setVisible(false);
        passLabel.setVisible(false);
        rePassLabel.setVisible(false);
        loginB.setVisible(false);
        signupB.setVisible(false);

        vacationTable.setVisible(true);
        maxPriceF.setVisible(true);
        minPriceF.setVisible(true);
        destF.setVisible(true);
        startDateF.setVisible(true);
        endDateF.setVisible(true);
        extraDetF.setVisible(true);

        vacationTable.setVisible(true);
        maxPriceL.setVisible(true);
        minPriceL.setVisible(true);
        destL.setVisible(true);
        startDateL.setVisible(true);
        endDateL.setVisible(true);
        extraDetL.setVisible(true);

        addB.setVisible(true);
        modifyB.setVisible(true);
        deleteB.setVisible(true);
        destB.setVisible(true);
        deleteDB.setVisible(true);

        maxPriceL.setText("Price");
        minPriceL.setText("Name");
        destL.setText("Destination");
        startDateL.setText("Period");
        endDateL.setText("Limit");

        TableColumn nameCol = new TableColumn("Name");
        TableColumn destCol = new TableColumn("Destination");
        TableColumn descriptionCol = new TableColumn("Description");
        TableColumn priceCol = new TableColumn("Price");
        TableColumn periodCol = new TableColumn("Period");
        TableColumn limitCol = new TableColumn("Limit");
        TableColumn statusCol = new TableColumn("Status");

        vacationTable.getColumns().clear();
        vacationTable.getItems().clear();

        nameCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("name")
        );
        destCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("destinationName")
        );
        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("extraDetails")
        );
        priceCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, Double>("price")
        );
        periodCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("period")
        );
        limitCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, Integer>("limitB")
        );
        statusCol.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("status")
        );


        vacationTable.getColumns().addAll(nameCol, destCol, descriptionCol, priceCol, periodCol, limitCol,statusCol);

        vacations = vc.getVacations();
        for (Vacation v : vacations) {
                vacationTable.getItems().add(v);
        }
    }


    @FXML
    protected void selectVacation() {
        if(loggedUser.getRole().equals("User")) {
            Vacation v = (Vacation) vacationTable.getSelectionModel().getSelectedItem();
            if (v != null) {
                em.getTransaction().begin();
                if (v.getUsers().contains(loggedUser)) {
                    v.deleteUser(loggedUser);
                    showMessage("Deleted",v.getName()+ " was unbooked.");

                } else {
                    v.addUser(loggedUser);
                    showMessage("Added",v.getName()+ " was booked.");
                }
                em.getTransaction().commit();
            }
        }
    }

    @FXML
    protected void searchVacation() {
        vacationTable.getItems().clear();
        try {
            double maxPrice, minPrice;
            if (minPriceF.getText().equals("")) {
                minPrice = 0;
            } else {
                minPrice = Double.parseDouble(minPriceF.getText());
            }
            if (maxPriceF.getText().equals("")) {
                maxPrice = 9999;
            } else {
                maxPrice = Double.parseDouble(maxPriceF.getText());
            }
            String date1,date2;
            if(startDateF.getText().equals("")){
                date1="00.00.0000";
            }
            else{
                date1=startDateF.getText();
            }
            if(endDateF.getText().equals("")){
                date2="30.12.9999";
            }
            else{
                date2=endDateF.getText();
            }
            boolean checkDest;
            if(destF.getText().equals("")){
                checkDest=false;
            }
            else {
                checkDest=true;
            }
            dateChecker dc=new dateChecker();
            for(Vacation v:vacations){
                if(v.getPrice()>=minPrice && v.getPrice()<=maxPrice){
                    if(dc.validDate(date1) && dc.validDate(date2) &&
                            dc.compDate(date1,v.getPeriod().substring(0,9)) && dc.compDate(date2,v.getPeriod().substring(11))){
                        if(!checkDest || v.getDestination().getName().equals(destF.getText())){
                            if(!bookedCheck.isSelected() || v.getUsers().contains(loggedUser)){
                                if (!v.getStatus().equals("BOOKED") || v.getUsers().contains(loggedUser)) {
                                    vacationTable.getItems().add(v);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            showMessage("Failure", "Something went wrong.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void addDestination(){
        String name=destF.getText();
        if(dc.addDestination(name)){
            showMessage("Success",name+ " was added.");
        }
        else {
            showMessage("Failure",name+ " already exists.");
        }
    }

    @FXML
    protected void addVacation(){
        String dest=destF.getText();
        String name=minPriceF.getText();
        String price=maxPriceF.getText();
        String exData=extraDetF.getText();
        String limit=endDateF.getText();
        String period=startDateF.getText();

        if(dest.equals("") || name.equals("") || price.equals("") || exData.equals("") || limit.equals("") || period.equals("")){
            showMessage("Missing indo","One or more fields are empty");
        }
        else {
            try {
                double realPrice=Double.parseDouble(price);
                Integer limitI=Integer.parseInt(limit);
                Destination d=dc.findDestination(dest);
                if(d==null){
                    showMessage("Failure",dest+ " was not found.");
                }
                else {
                    Vacation newV = vc.addVacation(name, d,exData,period,realPrice,limit);
                    vacations.add(newV);
                    showMessage("Success",name+ " was added.");

                    agencyPage();
                }
            }
            catch (Exception e){
                e.printStackTrace();
                showMessage("NAN","Wrong Number somewhere");
            }
        }

    }

    @FXML
    protected void deleteVacation(){
        String name=minPriceF.getText();
        if(vc.deleteVacation(name)){
            showMessage("Success",name+" was deleted.");
        }
        else {
            showMessage("Failure",name+ " was not found.");
        }
        agencyPage();
    }

    @FXML
    protected void deleteDestination(){
        String name=destF.getText();
        if(dc.deleteDestination(name,vc)){
            showMessage("Success",name+" was deleted.");
        }
        else{
            showMessage("Failure",name+ " was not found.");

        }
        agencyPage();
    }

    @FXML
    protected void modifyVacation(){
        String name=minPriceF.getText();
        if(name.equals("")){
            showMessage("Missing info","Please provide a name.");
        }
        else{
            Vacation v=vc.findVacation(name);
            if(v!=null){
                String price=maxPriceF.getText();
                String exData=extraDetF.getText();
                String limit=endDateF.getText();
                String period=startDateF.getText();

                try {
                    double priceReal;
                    int limitI;
                    if(!price.equals("")) {
                        priceReal = Double.parseDouble(price);
                        if(priceReal>0) {
                            v.setPrice(priceReal);
                        }
                        else {
                            showMessage("Invalid number", "Number must be higher than 0.");
                        }
                    }
                    if(!exData.equals("")){
                        v.setExtraDetails(exData);
                    }
                    if(!period.equals("")){
                        dateChecker dateChecker=new dateChecker();
                        if(dateChecker.validDate(period.substring(0,9)) && dateChecker.validDate(period.substring(11))
                               && dateChecker.compDate(period.substring(0,9),period.substring(11)) ){
                           v.setPeriod(period);
                        }
                        else{
                            showMessage("Invalid date","The period is invalid.");
                        }
                    }
                    if(!limit.equals("")){
                        limitI=Integer.parseInt(limit);
                        if(limitI>=v.getUsers().size()){
                            v.setLimitB(limit);
                        }
                        else {
                            showMessage("Invalid number","The limit is too small.");
                        }
                    }
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                    agencyPage();
                }
                catch (Exception e){
                    showMessage("Modify Package","Something went wrong");
                }
            }
        }
    }

    private void showMessage(String title, String text){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void setEm(EntityManager em) {
        this.em = em;
        uc = new UserController(em);
        vc = new VacationController(em);
        dc = new DestinationController(em);
    }
}
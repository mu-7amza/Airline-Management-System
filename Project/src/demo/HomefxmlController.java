/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class HomefxmlController implements Initializable {
    @FXML
    private TextField fullname_field;
    @FXML
    private TextField national_id_field;
    @FXML
    private ComboBox <String> country_from;
    @FXML
    private ComboBox <String> country_to;
    @FXML
    private DatePicker flight_date_field;
    @FXML
    private RadioButton one_trip_field;
    @FXML
    private RadioButton round_trip_field;
    @FXML
    private Button booknow_but;
    @FXML
    private Button logout_but;
    @FXML
    private Button contact_but;
    
    
    private String radeoValues ; 
    
    
    
 
    
    public void getValue(ActionEvent event){
        if(one_trip_field.isSelected()){
            radeoValues = "ONE TRIP";
            round_trip_field.setSelected(false);
    }  else if(round_trip_field.isSelected()){
         radeoValues = "ROUND TRIP";
        one_trip_field.setSelected(false);
    }
    }

  String countryFrom;
   String countryTo;
 

    /**
     * Initializes the controller class.
     */
 
     @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        country_from.setItems(FXCollections.observableArrayList("Egypt" , "Saudi Arabia" , "Morocco" , "China" , "France"));
         country_to.setItems(FXCollections.observableArrayList("Egypt" , "Saudi Arabia" , "Morocco" , "China" , "France"));
         
         contact_but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "ContactUs.fxml", "Contact us !", ""); //To change body of generated methods, choose Tools | Templates.
            }
        });
         
     
        // TODO
         booknow_but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!fullname_field.getText().trim().isEmpty() && !national_id_field.getText().trim().isEmpty()){
                    try {
                        String date = flight_date_field.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        countryFrom = String.valueOf(country_from.getValue());
                        countryTo = String.valueOf(country_to.getValue());
                        
                        DBUtils.bookFlight(event,fullname_field.getText(),national_id_field.getText(),countryFrom,countryTo,date,radeoValues);
                        
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SignupfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(HomefxmlController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                else{
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to book now !");
                    alert.show();
                }
            }
        });
        
        
        
        logout_but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event , "signinfxml.fxml","Log in !",null );

            }
        });
        
        

    }
  
    
}

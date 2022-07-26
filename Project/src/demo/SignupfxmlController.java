/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class SignupfxmlController implements Initializable {

    @FXML
    private TextField username_field;
    @FXML
    private TextField email_field;
    @FXML
    private TextField phone_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Button singup_But;
    @FXML
    private Button login_but;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
         singup_But.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!username_field.getText().trim().isEmpty() && !password_field.getText().trim().isEmpty()){
                    try {
                        DBUtils.signUpUser(event,username_field.getText(),password_field.getText(),email_field.getText(),phone_field.getText());
                        JOptionPane.showMessageDialog(null, "Signed Up Succesfully !");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SignupfxmlController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else{
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up !");
                    alert.show();
                }
            }
        });
           login_but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"signinfxml.fxml","Log in !",null);
            }
        });
    }    
    
}

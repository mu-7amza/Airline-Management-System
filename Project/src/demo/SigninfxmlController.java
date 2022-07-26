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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class SigninfxmlController implements Initializable {

 
    @FXML
    private Button login_but;
    @FXML
    private TextField username_field;
    @FXML
    private Button signup_but;
    @FXML
    private PasswordField password_field;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          signup_but.setOnAction((ActionEvent event) -> {
              DBUtils.changeScene(event,"signupfxml.fxml","Sign Up!",null);
          });
          
     login_but.setOnAction((ActionEvent event) -> {
         try {
             DBUtils.loginUser(event,username_field.getText(),password_field.getText());
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(SigninfxmlController.class.getName()).log(Level.SEVERE, null, ex);
         }
          });
    }
          
      @FXML
    private void signup(ActionEvent event) {
    }
    }    
    
    

   
    

  
    

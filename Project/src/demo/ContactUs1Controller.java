/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ContactUs1Controller implements Initializable {

    @FXML
    ComboBox<String> combox;
    @FXML
    private Button sendBut;
    @FXML
    private Button tellusBut;
    @FXML
    private Label succes;
    @FXML
    private TextField txt_field;
    @FXML
    private Button submit;
    @FXML
    private Button back;
    @FXML
    private Button cancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combox.setItems(FXCollections.observableArrayList("You have a problem during booking flight", "Suggest a new changes", "You entered a wrong data", "Want to cancel your flight"));
        sendBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                succes.setText("Succesfully Sent !");
            }
        });
        tellusBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txt_field.setVisible(true);

            }
        });
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                succes.setText("Submeted !");
                txt_field.clear();
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "signinfxml.fxml", "Contact", "");
            }
        });
           
        

    }
    }



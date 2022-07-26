/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class TableViewController implements Initializable {

    @FXML
    private TableView<Flight> table_view;
    @FXML
    private TableColumn<Flight, String> id_column;
    @FXML
    private TableColumn<Flight, String> name_column;
    @FXML
    private TableColumn<Flight, String> nationalId_column;
    @FXML
    private TableColumn<Flight, String> countryFrom_column;
    @FXML
    private TableColumn<Flight, String> countryTo_column;
    @FXML
    private TableColumn<Flight, String> date_column;
    @FXML
    private TableColumn<Flight, String> trip_column;
    @FXML
    private Button backbutt;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField filterKey;
    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_national_id;

    @FXML
    private ComboBox<String> txt_from;

    @FXML
    private ComboBox<String> txt_to;

    @FXML
    private DatePicker txt_date;

    @FXML
    private RadioButton one_trip_field;
    @FXML
    private RadioButton round_trip_field;
    private String radeoValues;

    public void getValue(ActionEvent event) {
        if (one_trip_field.isSelected()) {
            radeoValues = "ONE TRIP";
            round_trip_field.setSelected(false);
        } else if (round_trip_field.isSelected()) {
            radeoValues = "ROUND TRIP";
            one_trip_field.setSelected(false);
        }
    }
    String date;
    String countryFrom;
    String countryTo;
    int index = -1;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Flight flight = null;

    ObservableList<Flight> FlightList = FXCollections.observableArrayList();
    ObservableList<Flight> dataList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @FXML
    public void getSelected() {
        index = table_view.getSelectionModel().getSelectedIndex();

        if (index < -1) {
            return;

        }
        date = date_column.getCellData(index);
        txt_id.setText(id_column.getCellData(index));
        txt_username.setText(name_column.getCellData(index).toString());
        txt_national_id.setText(nationalId_column.getCellData(index).toString());

    }

    public void edit() {
        try {
            date = String.valueOf(txt_date.getValue());
            countryFrom = String.valueOf(txt_from.getValue());
            countryTo = String.valueOf(txt_to.getValue());

            String value1 = txt_id.getText();
            String value2 = txt_username.getText();
            String value3 = txt_national_id.getText();
            String value4 = countryFrom;
            String value5 = countryTo;
            String value6 = date;
            String value7 = radeoValues;
            connection = DBConnect.getConnect();
            String sql = "UPDATE order_inf SET order_id = '" + value1 + "',name = '" + value2 + "', national_id = '" + value3 + "',country_from = '" + value4 + "' ,country_to = '" + value5
                    + "',date = '" + value6 + "',trip_kind = '" + value7 + "' WHERE order_id = '" + value1 + "' ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            loadData();
            search();

        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_from.setItems(FXCollections.observableArrayList("Egypt", "Saudi Arabia", "Morocco", "China", "France"));
        txt_to.setItems(FXCollections.observableArrayList("Egypt", "Saudi Arabia", "Morocco", "China", "France"));

        loadData();
        search();

        edit();

        backbutt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "signinfxml.fxml", "", "");
            }
        });

    }

    public void delete() {
        connection = DBConnect.getConnect();
        String sql = "delete from order_inf where order_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txt_id.getText());
            preparedStatement.execute();
            loadData();
            search();
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void search() {

        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        nationalId_column.setCellValueFactory(new PropertyValueFactory<>("national_id"));
        countryFrom_column.setCellValueFactory(new PropertyValueFactory<>("from"));
        countryTo_column.setCellValueFactory(new PropertyValueFactory<>("to"));
        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        trip_column.setCellValueFactory(new PropertyValueFactory<>("trip"));

        dataList = DBConnect.getDataUsers();
        table_view.setItems(dataList);

        FilteredList<Flight> filteredList = new FilteredList<>(dataList, b -> true);
        filterKey.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(person -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (person.getName().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (person.getFrom().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (person.getNational_id().indexOf(searchKey) != -1) {
                    return true;
                } else if (person.getTrip().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (person.getTo().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Flight> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(table_view.comparatorProperty());

        table_view.setItems(sortedList);

    }

    public void Add_users() throws ClassNotFoundException, SQLException {

        date = String.valueOf(txt_date.getValue());
        countryFrom = String.valueOf(txt_from.getValue());
        countryTo = String.valueOf(txt_to.getValue());
        Class.forName("com.mysql.jdbc.Driver");
        connection = DBConnect.getConnect();

        preparedStatement = connection.prepareStatement("INSERT INTO order_inf (name , national_id , country_from , country_to , date , trip_kind) VALUES (?,?,?,?,?,?)");

        preparedStatement.setString(1, txt_username.getText());
        preparedStatement.setString(2, txt_national_id.getText());
        preparedStatement.setString(3, countryFrom);
        preparedStatement.setString(4, countryTo);
        preparedStatement.setString(5, date);
        preparedStatement.setString(6, radeoValues);

        preparedStatement.executeUpdate();

        loadData();
        search();
        txt_username.clear();
        txt_national_id.clear();
        txt_date.setValue(null);
        one_trip_field.setSelected(false);
        round_trip_field.setSelected(false);

    }

    @FXML
    private void refreshTable() {
        try {
            FlightList.clear();
            query = "SELECT order_id , name , national_id , country_from , country_to , date , trip_kind FROM order_inf";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FlightList.add(new Flight(
                        resultSet.getString("order_id"),
                        resultSet.getString("name"),
                        resultSet.getString("national_id"),
                        resultSet.getString("country_from"),
                        resultSet.getString("country_to"),
                        resultSet.getString("date"),
                        resultSet.getString("trip_kind")));
                table_view.setItems(FlightList);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadData() {
        connection = DBConnect.getConnect();
        refreshTable();
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        nationalId_column.setCellValueFactory(new PropertyValueFactory<>("national_id"));
        countryFrom_column.setCellValueFactory(new PropertyValueFactory<>("from"));
        countryTo_column.setCellValueFactory(new PropertyValueFactory<>("to"));
        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        trip_column.setCellValueFactory(new PropertyValueFactory<>("trip"));

        table_view.setItems(FlightList);

    }

}


package demo;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Hamza
 */

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

            } catch (IOException e) {
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();

    }

    public static void signUpUser(ActionEvent event, String username, String password, String email, String phone) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db", "root", "");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM user_inf WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("User Already Exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You can't use this username");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO user_inf (username , password , phone , email) VALUES (?,?,?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, phone);
                psInsert.setString(4, email);
                psInsert.executeUpdate();

                changeScene(event, "signinfxml.fxml", "Welcome!", username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null) {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Login Method for Login Button
    
    public static void loginUser(ActionEvent event, String username, String password) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM user_inf WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("User is not found in database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The provided Credentials are incorrect !");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if ((username.equals("admin") && password.equals("admin"))) {

                        changeScene(event, "TableView.fxml", "Welcome !", "");
                        JOptionPane.showMessageDialog(null, "Welcome Admin !");
                    } else if (retrievedPassword.equals(password)) {
                        changeScene(event, "homefxml.fxml", "Home", username);
                    } else {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect !");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void bookFlight(ActionEvent event, String name, String national_id, String country_from, String country_to, String date, String radeoValues) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement psInsert = null;
        ResultSet resultSet = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db", "root", "");

            psInsert = connection.prepareStatement("INSERT INTO order_inf (name , national_id , country_from , country_to , date , trip_kind) VALUES (?,?,?,?,?,?)");
            psInsert.setString(1, name);
            psInsert.setString(2, national_id);
            psInsert.setString(3, country_from);
            psInsert.setString(4, country_to);
            psInsert.setString(5, date);
            psInsert.setString(6, radeoValues);  
             if(national_id.length() !=14){
                    System.out.println("Please enter 14 number");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter valid national id number");
                    alert.show();
                }
             else if(country_from==country_to){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please choose the correct destination ");
                    alert.show();
             }
             else{
            

            psInsert.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Your Flight Reservation Is Saved !");
            alert.show();
             }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        if (psInsert != null) {
            try {
                psInsert.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}


package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DBConnect {
  
    private static Connection connection;
    
    

    public static Connection getConnect() {
        try {
           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db","root","");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    public static ObservableList<Flight> getDataUsers(){
        Connection co = getConnect();
        ObservableList<Flight> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = co.prepareStatement("SELECT order_id , name , national_id , country_from , country_to , date , trip_kind FROM order_inf");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
list.add(new Flight(rs.getString("order_id"), rs.getString("name"), rs.getString("national_id"),rs.getString("country_from"), rs.getString("country_to"), rs.getString("date"), rs.getString("trip_kind")));
                
            }
        } catch (SQLException e) {
        }
    return list;
    }
    
}

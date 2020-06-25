package sample;

import java.sql.*;

public class DBController {

    //Used to check connection to database
    Connection connection;

    public DBController(){
        connection = SqliteConnection.Connector();
        if (connection == null)
            System.out.println("Error connection to DB");
    }



    public boolean isDBConnected(){
        try{
           return !connection.isClosed();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}

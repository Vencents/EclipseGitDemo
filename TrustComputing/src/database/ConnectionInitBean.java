package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionInitBean {

	
    String userName="root";
    String userPassword="root";  
    private String driverName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/kxdl";

    Connection connection_inst;

    public ConnectionInitBean(){}
    /*
     * get a connection object to use later
     */
    public Connection getConnection(){
        try{
            Class.forName(driverName);
            connection_inst = DriverManager.getConnection(url,userName,userPassword);
        }
        catch(Exception ex){
            System.err.println("ConnectionInit.java getConnection(): " + ex.toString());
            connection_inst = null;
        }
        return connection_inst;
    }
    /*
     * close the connection with database
     */
    public void closeConnection(){
    	try{
        	connection_inst.close();
        	connection_inst = null;
    	}
    	catch(SQLException e){
    		System.out.println("ConnectionInit.java closeConnection(): " + e.toString());
    	}
    }
}

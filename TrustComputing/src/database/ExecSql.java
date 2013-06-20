package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.ConnectionInitBean;

public class ExecSql {
	   private Connection connection_inst;
	   private Statement stmt;
	   private ResultSet rs;
	   private ConnectionInitBean con;

	   public ExecSql(){
		   con = new ConnectionInitBean();
		   connection_inst = con.getConnection();
		   stmt = null;
		   rs = null;	
	   }
	   /* Updating database */
	   public boolean exUpdate(String strSql) {
		   try{
	   			stmt = connection_inst.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	   			stmt.executeUpdate(strSql);
	   			return true;            
		   }
		   catch(SQLException ex){
			   System.out.println("ExecSql.java exeUpdate(): " + ex.toString());
		       return false;
		   }	
	   }
	   
	   /* execute database query */
	   public ResultSet exeQuery(String strSql){	
		   try {
	   			stmt = connection_inst.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	   			rs = stmt.executeQuery(strSql);
		   }
		   catch(Exception ex){
			   System.out.println("ExecSql.java exeQuery(): " + ex.toString());
			   rs = null;
		   }	
		   return rs;
	    }
	   /* close connection using super method */
	   public void closeConnection() {
		   if(con != null) {
			   con.closeConnection();
			   con = null;
		   }
	   }
}

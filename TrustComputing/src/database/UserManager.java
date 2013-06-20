package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.ExecSql;

public class UserManager {
	public String sqlStr;
    public String userName;
    public String userPasswd;
	public int trustyValue;
	public ResultSet rs;
	public ExecSql exec;
	/*
	 * class construtor
	 */
	public UserManager(){
		sqlStr = "";
		userName = "";
		userPasswd = "";
		trustyValue = 0;
		exec = new ExecSql();
		rs = null;
	}
	/*
	 * Query database to make sure whether the user exists
	 * @param webUserName: user name
	 * @param password:user password
	 * @param type:user type
	 */
	public int isExist(String webUserName, String password, int type){
		if(webUserName.isEmpty() || password.isEmpty()){
			return 1;
		}
		else {
			sqlStr = "SELECT * FROM userInfo WHERE userName='" + webUserName + "' AND userPasswd='" 
							+ password + "' AND userType=" + type; 
			System.out.println("userManager.java isExist(): " + sqlStr);
			try{
				rs = exec.exeQuery(sqlStr);
				if(rs.next()) {
					if(0 == rs.getInt("onlineState") ) {/* The uesr name is legal and offline */
						return 3;
					}
					else{
						return 2;  /* 2 means the user already logged in */
					}
				}
				else {
					return 1;
				}
			}catch(Exception e){
				System.out.println("userManager.java isExist(): " + e.toString());
				return 1;
			}
		}
	}
	
	/*
	 * After the terminal sent platform information for evaluation, this method 
	 * is called to evaluate for web page login.
	 */
	public int isMember(String webUserName, String password, int type){
		if(webUserName.isEmpty() || password.isEmpty()){
			return 0;
		}
		else {
			sqlStr = "SELECT * FROM userInfo WHERE userName='" 
						+ webUserName + "' AND userPasswd='" + password + "' AND userType=" + type; 
			try{
				rs = exec.exeQuery(sqlStr);
				if(rs.next()) {
					//只有平台可信且用户信任值大于0才允许登陆
					if(rs.getString("trustingState").equals("trusted") && (rs.getInt("trustValue") > 0)) {
						/* update online state of current login user while the platform is trusted. */
						sqlStr = "UPDATE userInfo SET onlineState=1 WHERE userName='" + webUserName + "'";
						exec.exUpdate(sqlStr);
						/* record operation history */
						int userId = rs.getInt("userId");
						Date curTime = new Date();
						SimpleDateFormat dtfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String strDateTime = dtfmt.format(curTime);
						sqlStr = "INSERT INTO systemLog(operTime, userId, userName, operDesc) VALUES('"
									+ strDateTime + "', " + userId + ", '" + webUserName + "', 'The user login!')";
						exec.exUpdate(sqlStr);
						
						System.out.println("userManager.java isMember(): " + sqlStr);
						return 1;
					}
					else{
						return -1;  //Th code implies terminal platform is not safe.
					}
				}
				else {
					return 0;
				}
			}catch(Exception e){
				System.out.println("userManager.java isMember(): " + e.toString());
				return 0;
			}
		}
		
	}
	/*
	 * close connection with database using super method
	 */
	public void closeConnection(){
		if(exec != null){
			exec.closeConnection();
			exec = null;
		}
	}
	/*
	 * change last login time of current user.
	 * @return last login time as string format
	 */
	public String changeLastLogin(String webUserName, String password) {
		String lastLogin = null;
		String strDateTime = null;
		sqlStr = "SELECT lastLogin FROM userInfo WHERE userName='" 
				+ webUserName + "' AND userPasswd='" + password + "'";
		try{
			ResultSet rs = exec.exeQuery(sqlStr);
			if(rs.next()){
				lastLogin = rs.getString("lastLogin");  /* Get last login time record */
			}
			/* update login time as current time */
			Date curTime = new Date();
			SimpleDateFormat dtfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strDateTime = dtfmt.format(curTime);
			sqlStr = "UPDATE userInfo SET lastLogin='" + strDateTime 
						+ "' WHERE userName='" + webUserName + "' AND userPasswd='" + password + "'";
			exec.exUpdate(sqlStr);
		}catch(SQLException e){
			System.out.println("userManager.java changeLastLogin(): " + e.toString());
		}
		return lastLogin;
	}
	
	/*
	 * Retrie the ID of the specified user.
	 * Returns true ID of current user if no error occurs, otherwise returns -1.
	 */
	public int getID(String userName){
		int id = -1;
		ResultSet rs = null;
		sqlStr = "SELECT userId FROM userInfo WHERE userName='" + userName + "'";
		try{
			rs = exec.exeQuery(sqlStr);
			if(rs.next()){
				id = rs.getInt("userId");
			}
		}catch(SQLException e){
			System.out.println("userManager.java getID(): " + e.toString());
		}
		return id;
	}
	
	/*
	 * Determine if the specific user exists in the specific table.
	 * Returns ResultSet if the user exists, otherwise returns null.
	 */
	public ResultSet isUserExist(int userId, String table){
		ResultSet rs = null;
		sqlStr = "SELECT * FROM " + table + " WHERE userId=" + userId;
		rs = exec.exeQuery(sqlStr);
		return rs;
	}
}

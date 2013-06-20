package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ExecSql;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author zhangzhongke
 */
public class SessionValidate extends HttpServlet {

	/**
	 * It presents and must presents for serilization of servlet
	 */
	private static final long serialVersionUID = 1422791065299299789L;

	public SessionValidate() {
		super();
	}
	/**
	 * This method is called when a form has its tag value method equals to get.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * This method is called when a form has its tag value method equals to post.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = -1;
		ExecSql exec = new ExecSql();
		/* invalidate user session */
		HttpSession clientSession = request.getSession();
		String userName = (String) clientSession.getAttribute("userName");
		String strSql = "UPDATE userInfo SET onlineState=0 WHERE userName='" + userName + "'";
		exec.exUpdate(strSql);
		/* record system operation */
		strSql = "SELECT userId FROM userInfo WHERE userName='" + userName + "'";
		try{
			ResultSet rs = exec.exeQuery(strSql);
			if(rs.next()){
				userId = rs.getInt("userId");
			}
		}catch(SQLException e){
			System.out.println("sessionValidate.java doPost(): " + e.toString());
			exec.closeConnection();
		}
		
		Date curTime = new Date();
		SimpleDateFormat dtfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDateTime = dtfmt.format(curTime);
		strSql = "INSERT INTO systemLog(operTime, userId, userName, operDesc) VALUES('" 
					+ strDateTime + "', " + userId + ", '" + userName + "', 'The user leaves!')";
		if(exec.exUpdate(strSql)){
			clientSession.invalidate();
			System.out.println(strSql);
		}
		exec.closeConnection();
	}
}

package utilities;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import javax.servlet.http.HttpSession;
import database.ExecSql;;

/**
 * @author zhangzhongke
 */

public class AlterPasswd extends HttpServlet {
	
	private static final long serialVersionUID = 5737136762319653186L;

	/**
	 * Constructor of the object.
	 */
	public AlterPasswd() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String oldPw = request.getParameter("oldp");
		String newPw = request.getParameter("newp");
		
		HttpSession clientSession = request.getSession(false);
		String userName = (String) clientSession.getAttribute("userName");
		String userType = (String) clientSession.getAttribute("userType");
		int iuserType = Integer.parseInt(userType);
		
		ExecSql exec = new ExecSql();
		String strSql = "SELECT * FROM userInfo WHERE userName='" + userName + "'" + " AND userType=" + iuserType;
		
		ResultSet result = exec.exeQuery(strSql);
		try{
			if(result.next()){
				if(oldPw.equals(result.getString("userPasswd"))){
					strSql = "UPDATE userInfo SET userPasswd='" + newPw + "' WHERE userName='" + userName + "'";
					boolean bRes = exec.exUpdate(strSql);
					if(bRes){
						out.print("1");
					}
					else{
						out.print("0");
					}
				}
				else{
					out.write("0");
				}
			}
			else {
				out.write("0");
			}
		}
		catch(SQLException e){
			System.out.println("alterPasswd.java doPost(): " + e.toString());
		}
		finally{
			exec.closeConnection();
		}
	}	
}

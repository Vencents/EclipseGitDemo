package logicHandler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ExecSql;

public class AddUser extends HttpServlet {

	private static final long serialVersionUID = 4831327506843140563L;
	/**
	 * The doGet method of the servlet. <br>
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ExecSql exec = new ExecSql();
		String strSql;
		
		String name = request.getParameter("userName");
		String passwd = request.getParameter("userPasswd");
		String type = request.getParameter("userType");
		String trustingState = request.getParameter("trustingState");
		String trustLevel = request.getParameter("trustLevel");
		String trustValue = request.getParameter("trustValue");
		
		try{
			strSql = "INSERT INTO userInfo(userName, userPasswd, trustLevel, trustValue, trustingState, userType) VALUES('" 
				+ name + "', '" + passwd + "', " + trustLevel + ", " 
				+ trustValue + ", '" + trustingState + "', " + type + ")";
			if(exec.exUpdate(strSql)){
				out.print(1);
			}else{
				out.print(0);  //update user information failed
			}
		}catch(Exception e){
			System.out.println("AddUser.java: " + e.toString());
			exec.closeConnection();
		}
		exec.closeConnection();

		out.flush();
		out.close();
	}

}

package logicHandler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ExecSql;

public class ModifyTables extends HttpServlet {

	private static final long serialVersionUID = -3288397648431706546L;

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
		System.out.println(request.getQueryString());
		ExecSql exec = new ExecSql();
		String strSql;
		String operator = request.getParameter("oper");
		System.out.println(operator);
		if(operator.equals("edit")){  //edit record and save into database
			try{
				String name = request.getParameter("userName");
				String passwd = request.getParameter("userPasswd");
				String trustLevel = request.getParameter("trustLevel");
				String trustValue = request.getParameter("trustValue");
				String onlineState = request.getParameter("onlineState");
				String trustingState = request.getParameter("trustingState");
				String userType = request.getParameter("userType");
				String lastLogin = request.getParameter("lastLogin");
				String strUserId = request.getParameter("userId");
				strSql = "UPDATE userInfo SET userName='" + name + "', userPasswd='" + passwd + "', trustLevel=" 
							+ trustLevel + ", trustValue=" + trustValue + ", onlineState=" 
							+ onlineState + ", trustingState='" + trustingState  + "', userType=" + userType 
							+ ", lastLogin='" + lastLogin + "' WHERE userId=" + strUserId;
				exec.exUpdate(strSql);
			}catch(Exception e){
				System.out.println("modifyTables.java doPost()->edit: " + e.toString());
				exec.closeConnection();
			}
		}else {
			if(operator.equals("del")){  //delete record from database
				try{
					
					String userId = request.getParameter("userId");
					strSql = "DELETE FROM userInfo WHERE userId=" + userId;
					exec.exUpdate(strSql);
				}catch(Exception e){
					System.out.println("modifyTables.java doPost()->delete: " + e.toString());
					exec.closeConnection();
				}
			}
		}		
		exec.closeConnection();
		
		out.flush();
		out.close();
	}
}

package logicHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ExecSql;

public class ChangeState extends HttpServlet {

	private static final long serialVersionUID = -7237085342897431030L;

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
		
		String userId = request.getParameter("userId");
		String strSql = "SELECT onlineState FROM userInfo WHERE userId=" + userId;
		
		ExecSql exec = new ExecSql();
		ResultSet rs = exec.exeQuery(strSql);
		try{
			if(rs.next()){
				int st = rs.getInt("onlineState");
				if(st == 1){ //currently is online.
					//会话失效，更新用户在线状态
					HttpSession clientSession = request.getSession();
					clientSession.invalidate();
					strSql = "UPDATE userInfo SET onlineState=0 WHERE userId=" + userId;
					exec.exUpdate(strSql);
					out.print(0);
				}
				else{
					out.print(1);
				}
			}
		}catch(SQLException e){
			System.out.println("StateControl.java doPost(): " + e.toString());
		}finally{
			exec.closeConnection();
		}

		out.flush();
		out.close();
	}

}

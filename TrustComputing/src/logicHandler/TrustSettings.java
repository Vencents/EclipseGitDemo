package logicHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ExecSql;

public class TrustSettings extends HttpServlet {

	private static final long serialVersionUID = -6669045303264951267L;

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
		//stype: type, scapture: capture, spaste: paste, slogin: login, sratio: ratio
		String userType = request.getParameter("stype");
		String capture = request.getParameter("scapture");
		String paste = request.getParameter("spaste");
		String login = request.getParameter("slogin");
		String ratio = request.getParameter("sratio");
		String ratioText = request.getParameter("svalue");
		//如果数据库有数据则进行更新，否则将新数据插入数据库
		ExecSql exec = new ExecSql();
		String strSql = "SELECT * FROM trustSettings WHERE userType=" + userType;
		ResultSet rs = exec.exeQuery(strSql);
		try{
			if(rs.next()){  //更新数据库
				strSql = "UPDATE trustSettings SET capture=" + capture + ", copy=" + paste + 
									", failure=" + login + ", ratio=" + ratio + ", ratioCeiling=" + ratioText
									+ " WHERE userType=" + userType;
				
			}
			else{ //插入数据库
				strSql = "INSERT INTO trustSettings VALUES(" + userType + ", " + capture + ", " + paste
													+ ", " + login + ", " + ratio + ", " + ratioText + ")";
				System.out.println(strSql);
			}
			if(exec.exUpdate(strSql)){
				out.print(1);
			}else{
				out.print(0);
			}
			
		}catch(SQLException e){
			System.out.println("TrustSettings.java doPost(): " + e.toString());
		}finally{
			exec.closeConnection();
		}		

		out.flush();
		out.close();
	}

}

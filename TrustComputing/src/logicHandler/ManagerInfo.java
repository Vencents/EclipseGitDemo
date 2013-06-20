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
import net.sf.json.*;

public class ManagerInfo extends HttpServlet {

	private static final long serialVersionUID = -9114045037916810198L;

	/**
	 * The doGet method of the servlet. <br>
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String currentPage = request.getParameter("page");  //current page
		String rowsPerPage = request.getParameter("rows");  //rows per page
		
		int icurPage = Integer.parseInt(currentPage);
		int irowsPerPage = Integer.parseInt(rowsPerPage);

		/* calculate the number of total records of online*/
		String strSql = "SELECT COUNT(*) FROM userInfo WHERE userName<>'zhangzhongke'";
		int itotalCount = 0, itotalPages = 0;
		ResultSet userSet;
		ExecSql exec = new ExecSql();
		try{
			userSet = exec.exeQuery(strSql);
			if(userSet.next()){
				itotalCount = userSet.getInt(1);  /* 1 stands for the first column of the first row */
			}
		}catch(SQLException e){
			System.out.println("userTable.java doPost(): " + e.toString());
			exec.closeConnection();
		}
		/* calculate the total pages of all records */
		if(itotalCount != 0){
			itotalPages = (itotalCount % irowsPerPage) == 0?(itotalCount/irowsPerPage):((itotalCount/irowsPerPage) + 1);
		}
		else{
			itotalPages = 0;
		}
		try{
			int iStart = irowsPerPage * icurPage - irowsPerPage;  /* start point to query records */
			strSql = "SELECT * FROM userInfo WHERE userName<>'zhangzhongke' LIMIT " + iStart + ", " + irowsPerPage;
			userSet = exec.exeQuery(strSql);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("curPage", icurPage);
			jsonObject.put("totalPages", itotalPages);
			jsonObject.put("totalRecords", itotalCount);
			
			JSONArray myArray = new JSONArray();
			
			while(userSet.next()){
				JSONObject itemObject = new JSONObject();
				itemObject.put("userId", userSet.getInt("userId"));
				itemObject.put("lastLogin", userSet.getString("lastLogin"));
				itemObject.put("onlineState", userSet.getString("onlineState"));
				myArray.add(itemObject);
			}
			jsonObject.put("realData", myArray);
			out.print(jsonObject);
			
		}catch(Exception e){
			System.out.println(e.toString());
			exec.closeConnection();
		}
		exec.closeConnection();
		
		out.flush();
		out.close();		
	}

	/**
	 * The doPost method of the servlet. <br>
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String strSql;
		ResultSet rs;
		ExecSql exec = new ExecSql();
		String queryType = request.getParameter("type");
		if(queryType.equals("single")){
			String userName = request.getParameter("userName");
			System.out.println(userName.trim());
			
			strSql = "SELECT * FROM userInfo WHERE userName='" + userName.trim() + "'";
			try{
				rs = exec.exeQuery(strSql);
				JSONObject jobject = new JSONObject();
				if(rs.next()){
					jobject.put("userName", rs.getString("userName"));
					jobject.put("trustingState", rs.getString("trustingState"));
					jobject.put("trustLevel", rs.getString("trustLevel"));
					jobject.put("trustValue", rs.getString("trustValue"));
					jobject.put("userType", rs.getString("userType"));
					jobject.put("lastLogin", rs.getString("lastLogin"));
				}
				out.print(jobject);
				System.out.println(jobject.toString());
			}catch(SQLException sql){
				System.out.println(sql.toString());
				exec.closeConnection();
			}
		}
		else{
			String currentPage = request.getParameter("page");  //current page
			String rowsPerPage = request.getParameter("rows");  //rows per page
			
			int icurPage = Integer.parseInt(currentPage);
			int irowsPerPage = Integer.parseInt(rowsPerPage);

			/* calculate the number of total records */
			
			strSql = "SELECT COUNT(*) FROM userInfo";
			int itotalCount = 0, itotalPages = 0;
			ResultSet userSet;
			try{
				userSet = exec.exeQuery(strSql);
				if(userSet.next()){
					itotalCount = userSet.getInt(1);  /* 1 stands for the first column of the first row */
				}
			}catch(SQLException e){
				System.out.println(e.toString());
				exec.closeConnection();
			}
			/* calculate the total pages of all records */
			if(itotalCount != 0){
				itotalPages = (itotalCount % irowsPerPage) == 0?(itotalCount/irowsPerPage):((itotalCount/irowsPerPage) + 1);
			}
			else{
				itotalPages = 0;
			}
			
			try{
				int iStart = irowsPerPage * icurPage - irowsPerPage;  /* start point to query records */
				strSql = "SELECT * FROM userInfo LIMIT " + iStart + ", " + irowsPerPage;
				userSet = exec.exeQuery(strSql);

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("curPage", icurPage);
				jsonObject.put("totalPages", itotalPages);
				jsonObject.put("totalRecords", itotalCount);
				JSONArray myArray = new JSONArray();
				while(userSet.next()){
					JSONObject itemObject = new JSONObject();
					itemObject.put("userId", userSet.getInt("userId"));
					itemObject.put("userName", userSet.getString("userName"));
					itemObject.put("userPasswd", userSet.getString("userPasswd"));
					itemObject.put("trustLevel", userSet.getString("trustLevel"));
					itemObject.put("trustValue", userSet.getString("trustValue"));
					itemObject.put("onlineState", userSet.getString("onlineState"));
					itemObject.put("trustingState", userSet.getString("trustingState"));
					itemObject.put("userType", userSet.getString("userType"));
					itemObject.put("lastLogin", userSet.getString("lastLogin"));
					myArray.add(itemObject);
				}
				jsonObject.put("realData", myArray);
				out.print(jsonObject);
			}catch(Exception e){
				System.out.println(e.toString());
				exec.closeConnection();
			}
		}
		exec.closeConnection();
		
		out.flush();
		out.close();
	}
}

package logicHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import database.ExecSql;

public class SystemLogger extends HttpServlet {

	private static final long serialVersionUID = -8141810210455871481L;

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
 
		String currentPage = request.getParameter("page");  //current page
		String rowsPerPage = request.getParameter("rows");  //rows per page
		
		int icurPage = Integer.parseInt(currentPage);
		int irowsPerPage = Integer.parseInt(rowsPerPage);

		/* calculate the number of total records */
		int itotalCount = 0, itotalPages = 0;
		int iStart;
		ResultSet rs = null;
		ExecSql exec = new ExecSql();
		String strSql;
		String type = request.getParameter("type");
		System.out.println(type);
		if(type.equals("single")){
			strSql  = "SELECT COUNT(*) FROM systemLog WHERE userId=1";
			try{
				rs = exec.exeQuery(strSql);
				if(rs.next()){
					itotalCount = rs.getInt(1);  /* 1 stands for the first column of the first row */
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
				iStart = irowsPerPage * icurPage - irowsPerPage;  /* start point to query records */
				strSql = "SELECT * FROM systemLog WHERE userId=1 LIMIT " + iStart + ", " + irowsPerPage;
				rs = exec.exeQuery(strSql);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("curPage", icurPage);
				jsonObject.put("totalPages", itotalPages);
				jsonObject.put("totalRecords", itotalCount);
				
				JSONArray myArray = new JSONArray();
				
				while(rs.next()){
					JSONObject itemObject = new JSONObject();
					itemObject.put("itemNumber", rs.getInt("itemNumber"));
					itemObject.put("operTime", rs.getString("operTime"));
					itemObject.put("userId", rs.getString("userId"));
					itemObject.put("userName", rs.getString("userName"));
					itemObject.put("operDesc", rs.getString("operDesc"));
					myArray.add(itemObject);
				}
				jsonObject.put("realData", myArray);
				out.print(jsonObject);
			}catch(Exception e){
				System.out.println(e.toString());
				exec.closeConnection();
			}
		}
		else{
			strSql = "SELECT COUNT(*) FROM systemLog";
			try{
				rs = exec.exeQuery(strSql);
				if(rs.next()){
					itotalCount = rs.getInt(1);  /* 1 stands for the first column of the first row */
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
				iStart = irowsPerPage * icurPage - irowsPerPage;  /* start point to query records */
				strSql = "SELECT * FROM systemLog LIMIT " + iStart + ", " + irowsPerPage;
				rs = exec.exeQuery(strSql);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("curPage", icurPage);
				jsonObject.put("totalPages", itotalPages);
				jsonObject.put("totalRecords", itotalCount);
				
				JSONArray myArray = new JSONArray();
				
				while(rs.next()){
					JSONObject itemObject = new JSONObject();
					itemObject.put("itemNumber", rs.getInt("itemNumber"));
					itemObject.put("operTime", rs.getString("operTime"));
					itemObject.put("userId", rs.getString("userId"));
					itemObject.put("userName", rs.getString("userName"));
					itemObject.put("operDesc", rs.getString("operDesc"));
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

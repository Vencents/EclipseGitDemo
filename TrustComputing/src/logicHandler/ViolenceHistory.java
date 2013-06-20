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

public class ViolenceHistory extends HttpServlet {

	private static final long serialVersionUID = -698154464070128084L;

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
		
		String strSql = "SELECT COUNT(*) FROM operInfo";
		int itotalCount = 0, itotalPages = 0;
		ResultSet userSet;
		ExecSql exec = new ExecSql();
		try{
			userSet = exec.exeQuery(strSql);
			if(userSet.next()){
				itotalCount = userSet.getInt(1);  /* 1 stands for the first column of the first row */
			}
		}catch(SQLException e){
			System.out.println("TerminalAction.java doPost() 1: " + e.toString());
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
			strSql = "SELECT * FROM operInfo LIMIT " + iStart + ", " + irowsPerPage;
			userSet = exec.exeQuery(strSql);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("curPage", icurPage);
			jsonObject.put("totalPages", itotalPages);
			jsonObject.put("totalRecords", itotalCount);
			
			JSONArray myArray = new JSONArray();
			
			while(userSet.next()){
				JSONObject itemObject = new JSONObject();
				itemObject.put("userName", userSet.getString("userName"));
				itemObject.put("operType", userSet.getString("operType"));
				itemObject.put("operTime", userSet.getString("operTime"));
				itemObject.put("operContent", userSet.getString("operContent"));
				itemObject.put("operDesc", userSet.getString("operDesc"));
				myArray.add(itemObject);
			}
			jsonObject.put("realData", myArray);
			out.print(jsonObject);
		}catch(Exception e){
			System.out.println("TerminalAction.java doPost() 2: " + e.toString());
			exec.closeConnection();
		}
		exec.closeConnection();

		out.flush();
		out.close();
	}

}

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
import database.UserManager;
import database.StringParser;

public class ProcessInfo extends HttpServlet {

	private static final long serialVersionUID = 5061798659802031168L;

	/**
	 * The doGet method of the servlet. <br>
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId, count, i;
		String strSql;
		String subFields[]; /* Sub fields for process infos */
		UserManager manager = new UserManager();
		ExecSql exec = new ExecSql();
		StringParser parser = new StringParser();
		String cryptData = request.getQueryString();
/* For mobile, data format should be like "userName=xxx;platform=xxx;processName=xxx,pid=xxx,uid=xxx,memory=xxx;" */
/* For PC, data format should be like "userName=xxx;platform=xxx;processName=xxx,pid=xxx,owner=xxx,memory=xxx" */
		String dataFields[] = cryptData.split(";");
		String strName = parser.retriveData(dataFields[0], '=');
		userId = manager.getID(strName);
		/* the platform is mobile, then retrieve the specific data fields. */
		if(parser.retriveData(dataFields[1], '=') == "mobile"){
			count = dataFields.length; //the total count of all elements in array.
			for(i = 2; i < count; i++ ){
				subFields = dataFields[i].split(",");
				strSql = "INSERT INTO processInfo(userId, procId, procName, usedMem, procOwner) VALUES(" + userId
						 + ", " + parser.retriveData(subFields[1], '=') + ", '" 
						 + parser.retriveData(subFields[0], '=') + "', '"
						 + parser.retriveData(subFields[3], '=') + "', '" 
						 + parser.retriveData(subFields[2], '=') + "')";
				if(!exec.exUpdate(strSql)){
					System.out.println("Update processInfo failed!");
				}
			}
		}
		/* The terminal platform is Windows */
		else{
			count = dataFields.length;
			for(i = 2; i < count; i++){ /* insert all process record into database in a loop */
				subFields = dataFields[i].split(",");
				strSql = "INSERT INTO processInfo(userId, procId, procName, usedMem, procOwner) VALUES(" + userId
						+ ", " + parser.retriveData(subFields[1], '=') + ", '" 
						+ parser.retriveData(subFields[0], '=') + "', '"
						+ parser.retriveData(subFields[3], '=') + "', '"
						+ parser.retriveData(subFields[2], '=') + "')";
				if(!exec.exUpdate(strSql)){
					System.out.println("Update processInfo failed!");
				}
			}
		}
		exec.closeConnection();
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
		String strUserId =  request.getParameter("userId");
		
		int icurPage = Integer.parseInt(currentPage);
		int irowsPerPage = Integer.parseInt(rowsPerPage);

		/* calculate the number of total records of online*/
		String strSql = "SELECT COUNT(*) FROM processInfo WHERE userId=" + strUserId;
		int itotalCount = 0, itotalPages = 0;
		ResultSet rs;
		ExecSql exec = new ExecSql();
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
			int iStart = irowsPerPage * icurPage - irowsPerPage;  /* start point to query records */
			strSql = "SELECT * FROM processInfo WHERE userId=" + strUserId;
			strSql += " LIMIT " + iStart + ", " + irowsPerPage;
			
			rs = exec.exeQuery(strSql);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("curPage", icurPage);
			jsonObject.put("totalPages", itotalPages);
			jsonObject.put("totalRecords", itotalCount);
			
			JSONArray myArray = new JSONArray();
			
			while(rs.next()){
				JSONObject itemObject = new JSONObject();
				itemObject.put("procId", rs.getInt("procId"));
				itemObject.put("procName", rs.getString("procName"));
				itemObject.put("usedMem", rs.getString("usedMem"));
				itemObject.put("usedCPU", rs.getString("usedCPU"));
				itemObject.put("procOwner", rs.getString("procOwner"));
				myArray.add(itemObject);
			}
			jsonObject.put("realData", myArray);
			out.print(jsonObject);
			
		}catch(SQLException e){
			System.out.println(e.toString());
			exec.closeConnection();
		}
		exec.closeConnection();
		
		out.flush();
		out.close();
	}

}

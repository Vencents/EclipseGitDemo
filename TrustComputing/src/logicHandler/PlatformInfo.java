package logicHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.StringParser;
import database.UserManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import database.ExecSql;

public class PlatformInfo extends HttpServlet {

	private static final long serialVersionUID = -6702229482892874578L;

	/**
	 * The doGet method of the servlet. <br>
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int userId = 0;
		//String res;   /* results to be sent to client */
		String strSql;
		ResultSet rs = null;
		ExecSql exec = new ExecSql();
		UserManager manager = new UserManager();
		String queryString = request.getQueryString();
		StringParser myDecoder = new StringParser();
		
		String argvs[] = queryString.split(";");
		String platform = myDecoder.retriveData(argvs[0], '=');
		String remoteIP = request.getRemoteAddr();
		int remotePort = request.getRemotePort();
		/* check whether the environment of pc is safe or not */
		if(platform.equals("mobile")){
			String mobileType = myDecoder.retriveData(argvs[1], '=');  /* retrive responding fields */
			String systemVer = myDecoder.retriveData(argvs[2], '=');
			String cpuType = myDecoder.retriveData(argvs[3], '=');
			String cpuFreq = myDecoder.retriveData(argvs[4], '=');
			String memSize = myDecoder.retriveData(argvs[5], '=');
			String memAvail = myDecoder.retriveData(argvs[6], '=');
			String permission = myDecoder.retriveData(argvs[7], '=');
			String strName = myDecoder.retriveData(argvs[8], '=');

			try{
				userId = manager.getID(strName);
				if(userId != -1){
					rs = manager.isUserExist(userId, "termInfo");
					if(rs.next()){
						String systemVer_t = rs.getString("termOS");
						String mobileInfo_t = rs.getString("termType");
						String permission_t = rs.getString("perm");
						rs.close();
						if(systemVer_t.equals(systemVer) && mobileInfo_t.equals("mobile/" + mobileType)
								&& permission_t.equals(permission)){
							/* change the online state of current user */
							strSql = "UPDATE userInfo SET trustingState='trusted' WHERE userId=" + userId;
							exec.exUpdate(strSql);
							out.print("trusted");
						}
						else{  // The platform is not safety, update the records of table.
							strSql = "UPDATE userInfo SET trustingState='untrusted' WHERE userId=" + userId;
							exec.exUpdate(strSql);
							out.print("untrusted");
						}
					}
					else {
						strSql = "INSERT INTO termInfo(userId, termType, termIP, termOS, CPUinfo, MEMinfo, perm) VALUES(";
						strSql += userId + ", 'mobile/" + mobileType + "', '" + remoteIP + "/" + remotePort + "', '" 
									 + systemVer + "', '" + cpuType + "/" + cpuFreq + "', '" + memAvail + "/" + memSize + "', '" 
									 + permission + "')";
						System.out.println("initialInfo.java doGet()->mobile 1: " + strSql);
						exec.exUpdate(strSql);
							/* change the trusting state of current user */
						strSql = "UPDATE userInfo SET trustingState='trusted' WHERE userId=" + userId;
						exec.exUpdate(strSql);
							/* encrypt results using xor */
						out.print("trusted");
					}
				}
			}catch(SQLException e){
				System.out.println("Platform.java doGet()->mobile 2: " + e.toString());
			}
		}
		else {
			String osType = myDecoder.retriveData(argvs[1], '=');
			String nicType = myDecoder.retriveData(argvs[2], '=');
			String memInfo = myDecoder.retriveData(argvs[3], '=');
			String cpuInfo = myDecoder.retriveData(argvs[4], '=');
			String hostName = myDecoder.retriveData(argvs[5], '=');
			String strName = myDecoder.retriveData(argvs[6], '=');  //the name of current user, appended after the real data
			
			try{
				userId = manager.getID(strName);
				/* The user exists in database, then computes his trusting level. */
				if(userId != -1){
					rs = manager.isUserExist(userId, "termInfo");
					if(rs.next()){
						String termOS = rs.getString("termOS");
						String termType = rs.getString("termType");
						String nicInfo = rs.getString("NICinfo");
						rs.close();
						if(termOS.equals(osType) && termType.equals("pc/windows")
							&& nicInfo.equals(nicType)){
							/* change the online state of current user */
							strSql = "UPDATE userInfo SET trustingState='trusted' WHERE userId=" + userId;
							out.print("trusted");
						}
						else{
							strSql = "UPDATE userInfo SET trustingState='untrusted' WHERE userId=" + userId;
							out.print("untrusted");
						}
						exec.exUpdate(strSql);
					}
					else {  //If the platform information doesn't exist in the 'termInfo' table, then update the table.
						strSql = "INSERT INTO termInfo(userId, termType, termName, termIP, termOS, CPUinfo, MEMinfo, NICinfo) VALUES(";
						strSql += userId + ", 'pc/windows', '" + hostName + "', '"+ remoteIP + "/" + remotePort + "', '" + osType + "', '"
								  + cpuInfo + "', '" + memInfo + "', '" + nicType + "')";
						System.out.println("initialInfo.java doGet()->pc 1:" + strSql);
						exec.exUpdate(strSql);
						strSql = "UPDATE userInfo SET trustingState='trusted' WHERE userId=" + userId;
						exec.exUpdate(strSql);
						out.print("trusted");
					}
				}
			}catch(Exception e){
				System.out.println("Platform.java doGet()->pc 2:" + e.toString());
			}
		}
		exec.closeConnection();
		manager.closeConnection();

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
		String currentPage = request.getParameter("page");  //current page
		String rowsPerPage = request.getParameter("rows");  //rows per page
		
		int icurPage = Integer.parseInt(currentPage);
		int irowsPerPage = Integer.parseInt(rowsPerPage);

		/* calculate the number of total records of online*/
		String strSql = "SELECT COUNT(*) FROM termInfo";
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
			strSql = "SELECT * FROM termInfo LIMIT " + iStart + ", " + irowsPerPage;
			rs = exec.exeQuery(strSql);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("curPage", icurPage);
			jsonObject.put("totalPages", itotalPages);
			jsonObject.put("totalRecords", itotalCount);

			JSONArray myArray = new JSONArray();
			while(rs.next()){
				JSONObject itemObject = new JSONObject();
				itemObject.put("userId", rs.getString("userId"));
				itemObject.put("termType", rs.getString("termType"));
				itemObject.put("termName", rs.getString("termName"));
				itemObject.put("termIP", rs.getString("termIP"));
				itemObject.put("termOS", rs.getString("termOS"));
				itemObject.put("CPUinfo", rs.getString("CPUinfo"));
				itemObject.put("MEMinfo", rs.getString("MEMinfo"));
				itemObject.put("NICinfo", rs.getString("NICinfo"));
				itemObject.put("perm", rs.getString("perm"));
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

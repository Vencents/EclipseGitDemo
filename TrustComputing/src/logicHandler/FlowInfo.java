/*
 * This servlet is designed to receive flow information of 
 * client, and update infos in database.
 */
package logicHandler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.StringParser;
import database.UserManager;
import database.ExecSql;
import java.sql.ResultSet;
import java.sql.SQLException;
//Including for JSON request
import net.sf.json.JSONObject;
/**
 * @author zhangzhongke
 * @date 2012/12/26
 * @version 1.0
 */
public class FlowInfo extends HttpServlet {
	/**
	 * serialVersionID must be different from each servlet
	 */
	private static final long serialVersionUID = 5565100362084536555L;

	/**
	 * The doGet method of the servlet.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		StringParser decoder = new StringParser();
		UserManager manager = new UserManager();
		ExecSql exec = new ExecSql();
		String initialData = request.getQueryString();
		/* the content of realData is "userName=xxx;buisiness=xxx;nonbuisiness=xxx" */
		String fields[] = initialData.split(";");
		String strName = decoder.retriveData(fields[0], '='); //userName
		String strBuis = decoder.retriveData(fields[1], '='); //buisiness
		String strNonBuis = decoder.retriveData(fields[2], '='); //non buisiness
		/* buisiness flow and none buisiness flow */
		int iBuis = Integer.parseInt(strBuis);
		int iNonBuis = Integer.parseInt(strNonBuis);
		int iTotal = 0, iBuisTemp = 0, iNonBuisTemp = 0, iTotalTemp = 0;
		String strSql;
		ResultSet rs = null;
		/* retrive user ID */
		int userId = manager.getID(strName);
		rs = manager.isUserExist(userId, "flowInfo");
		try{
			if(rs.next()){ 
				iBuisTemp = rs.getInt("totalBusiFlow");
				iNonBuisTemp = rs.getInt("totalNonBusiFlow");
				iTotalTemp = rs.getInt("totalFlow");
				rs.close();
					
				iTotalTemp += iBuis + iNonBuis;  //increment of total data flow.
				iBuisTemp += iBuis;    //increment of buisiness data flow
				iNonBuisTemp += iNonBuis; //increment of nonbuisiness data flow
				
				strSql = "UPDATE flowInfo SET totalBusiFlow=" + iBuisTemp + ", totalNonBusiFlow=" + iNonBuisTemp
							+ ", totalFlow=" + iTotalTemp + ", curBusiFlow=" + iBuis + ", curNonBusiFlow=" + iNonBuis + " WHERE userId=" + userId;
				if(!exec.exUpdate(strSql)){
					System.out.println("FlowInfo.java doGet(): update databases failed!");
				}
			}
			else{ /* current user doesn't exist in flowInfo table. */
				rs.close();
				iTotal = iBuis + iNonBuis;
				strSql = "INSERT INTO flowInfo(userId, curBusiFlow, curNonBusiFlow, totalBusiFlow, totalNonBusiFlow, totalFlow) VALUES(" 
						+ userId + ", " + iBuis + ", " + iNonBuis + ", " + iBuis + ", " + iNonBuis + ", " + iTotal + ")";
				if(!exec.exUpdate(strSql)){
					System.out.println("FlowInfo.java doGet() 2: update databases failed!");
				}
			}
		}catch(SQLException e){
			System.out.println("FlowInfo.java doGet() 1:" + e.toString());
			exec.closeConnection();
			manager.closeConnection();				
		}
		int tl, tv;
		tl = tv = 0;
		
		strSql = "SELECT trustLevel, trustValue FROM userInfo WHERE userId=" + userId;
		rs = exec.exeQuery(strSql);
		try{
			if(rs.next()){
				tv = rs.getInt("trustValue");
				rs.close();
			}
		}catch(SQLException e){
			System.out.println("FlowInfo.java doGet() 3: " + e.toString());
			exec.closeConnection();
		}
		int ratio = 0;
		float ceiling = 0;
		strSql = "SELECT ratio, ratioCeiling FROM trustSettings WHERE userType=0";
		rs = exec.exeQuery(strSql);
		try{
			if(rs.next()){
				ratio = rs.getInt("ratio");
				ceiling = rs.getFloat("ratioCeiling");
				rs.close();
			}
		}catch(SQLException e){
			System.out.println("FlowInfo.java doGet() 4: " + e.toString());
			exec.closeConnection();
		}
		//流量比例超过极限，信任度减少一个单位
		if(iBuis == 0){
			tv -= ratio;
		}
		else if(iNonBuis/iBuis > ceiling){
			tv -= ratio;
		}
		if(tv > 75){
			tl = 0;
			strSql = "UPDATE userInfo SET trustLevel=" + tl + ", trustValue=" + tv + " WHERE userId=" + userId;
		}
		else if(tv > 50){
			tl = 1;
			strSql = "UPDATE userInfo SET trustLevel=" + tl + ", trustValue=" + tv + " WHERE userId=" + userId;
		}
		else if(tv > 25){
			tl = 2;
			strSql = "UPDATE userInfo SET trustLevel=" + tl + ", trustValue=" + tv + " WHERE userId=" + userId;
		}
		else if(tv > 0){
			tl = 3;
			strSql = "UPDATE userInfo SET trustLevel=" + tl + ", trustValue=" + tv + " WHERE userId=" + userId;
		}
		else {  //已经超鬼了
			tl = 3;
			tv = 0;
			HttpSession session = request.getSession();
			session.invalidate();
			strSql = "UPDATE userInfo SET onlineState=0, trustLevel=" + tl + ", trustValue=" + tv + " WHERE userId=" + userId;
			
		}
		exec.exUpdate(strSql);
		exec.closeConnection();
		out.flush();
		out.close();
	}

	/**
	 * The doPost method accepts ajax requests from server internal, 
	 * and then echo results with JSON format to presentation page to paint plot area.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		/*The parameter 'userId' passed from Ajax request from reviewChart.html */
		String strUserId = request.getParameter("userId");
		double iTempB, iTempNB;
		ResultSet rs = null;
		ExecSql exec = new ExecSql();
		String strSql = "SELECT * FROM flowInfo WHERE userId=" + strUserId;
		try{
			rs = exec.exeQuery(strSql);
			if(rs.next()){
				iTempB = rs.getInt("curBusiFlow")/1024.0;
				iTempNB = rs.getInt("curNonBusiFlow")/1024.0;
				JSONObject jsonObject = new JSONObject();
				//保留两位小数
				jsonObject.put("buisiness",  iTempB);
				jsonObject.put("nonBuisiness", iTempNB);
				out.print(jsonObject);
			}
			else{
				/* All fields use default values expcet 'userId' */
				strSql = "INSERT INTO flowInfo(userId) VALUES(" + strUserId + ")";
				exec.exUpdate(strSql);
			}
		}catch(SQLException e){
			System.out.println("FlowInfo.java doPost(): " + e.toString());
		}finally{
			exec.closeConnection();
		}
	}
}

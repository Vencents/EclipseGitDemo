package logicHandler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.StringParser;
import database.ExecSql;
/**
 * @author zhangzhongke
 * @date 2013/4/7
 * @version 1.0
 * @description: 这个servlet用于接收客户端搜集的用户行为信息，并根据这些行为信息
 *      		  调整用户的信任度值。
 */
public class AdjustTrustLevel extends HttpServlet {

	private static final long serialVersionUID = -3800492043465450546L;
	/**
	 * This method is called when a form has its tag value method equals to get.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * This method is called when a form has its tag value method equals to post.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String contents;  //操作内容
		String description;
		String results = "normal";
		int cap, cop, log;
		cap = cop = log = 0;

		StringParser decoder = new StringParser();
		String initialData = request.getQueryString();
		
		String fields[] = initialData.split(";");
		String uName = decoder.retriveData(fields[0], '='); //userName
		String oType = decoder.retriveData(fields[1], '='); //type
		if(oType.equals("0")){
			contents = "capture";
			description = "The user used screenshot 1 time.";
			cap = 1;
		}
		else if(oType.equals("1")){
			contents = "copy";
			description = "The user used copy clipboard 1 time.";
			cop = 1;
		}
		else if(oType.equals("2")) { //类型2代表登陆出现失败
			String t = decoder.retriveData(fields[2], '='); //times
			contents = "login";
			description = "The user logining failed for " + t + " times.";
			log = 1;
		}
		else{  //may never reached at this clause.
			contents = "flow";
			description = "The data low is out of limits...";
		}
		//先在数据库插入违规行为记录
		Date curTime = new Date();
		SimpleDateFormat dtfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDateTime = dtfmt.format(curTime);
		String strSql = "INSERT INTO operInfo VALUES('" + uName + "', " + oType + ", '" + strDateTime
							+ "', '" + contents + "', '" + description + "')";
		ExecSql exec = new ExecSql();
		exec.exUpdate(strSql);
		
		//再对信任度进行操作
		int tl,tv;
		tl = tv = 0;
		strSql = "SELECT trustLevel, trustValue FROM userInfo WHERE userName='" + uName + "'";
		ResultSet rs = exec.exeQuery(strSql);
		try{
			if(rs.next()){
				tv = rs.getInt("trustValue");
				rs.close();
			}
		}catch(SQLException e){
			System.out.println("AdjustTrustLevel.java doPost():1 " + e.toString());
		}
		int cap_t, cop_t, fail_t;
		cap_t = cop_t = fail_t = 0;
		strSql = "SELECT * FROM trustSettings WHERE userType=0";
		rs = exec.exeQuery(strSql);
		try{
			if(rs.next()){
				cap_t = rs.getInt("capture");
				cop_t = rs.getInt("copy");
				fail_t = rs.getInt("failure");
				rs.close();
			}
		}catch(SQLException e){
			System.out.println("AdjustTrustLevel.java doPost():2 " + e.toString());
		}
		//信任等级，每25分一个梯次
		tv -= (cap*cap_t + cop*cop_t + log*fail_t);
		if(tv > 75){
			tl = 0;
			strSql = "UPDATE userInfo SET trustLevel=" + tl 
					+ ", trustValue=" + tv + " WHERE userName='" + uName + "'";	
		}
		else if(tv > 50){
			tl = 1;
			strSql = "UPDATE userInfo SET trustLevel=" + tl 
					+ ", trustValue=" + tv + " WHERE userName='" + uName + "'";	
		}
		else if(tv > 25){
			tl = 2;
			strSql = "UPDATE userInfo SET trustLevel=" + tl 
						+ ", trustValue=" + tv + " WHERE userName='" + uName + "'";	
		}
		else if(tv > 0){
			tl = 3;
			strSql = "UPDATE userInfo SET trustLevel=" + tl 
						+ ", trustValue=" + tv + " WHERE userName='" + uName + "'";	
		}
		else {  //已经超鬼了
			tl = 3;
			tv = 0;
			results = "forbid";
			HttpSession session = request.getSession();
			session.invalidate();
			strSql = "UPDATE userInfo SET onlineState=0, trustLevel=" + tl 
						+ ", trustValue=" + tv + " WHERE userName='" + uName + "'";	
		}
		exec.exUpdate(strSql);
		exec.closeConnection();
		
		out.print(results);
		out.flush();
		out.close();
	}
}

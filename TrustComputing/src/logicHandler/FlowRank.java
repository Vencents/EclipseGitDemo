package logicHandler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import database.ExecSql;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlowRank extends HttpServlet {

	private static final long serialVersionUID = -5205001875582990357L;

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

		String count = request.getParameter("count");
		String strSql = "SELECT userName, totalBusiFlow, totalNonBusiFlow FROM userInfo, flowInfo " +
				"WHERE userInfo.userId IN(SELECT userId FROM flowInfo ORDER BY totalFlow DESC) LIMIT 0," + count ;
		ExecSql exec = new ExecSql();
		ResultSet rs = exec.exeQuery(strSql);
		JSONArray myArray = new JSONArray();
		double b, nb;
		try{
			while(rs.next()){
				JSONObject itemObject = new JSONObject();
				itemObject.put("name", rs.getString("userName"));
				b = rs.getInt("totalBusiFlow")/1024.0;
				nb = rs.getInt("totalNonBusiFlow")/1024.0;
				itemObject.put("b", b);
				itemObject.put("nb", nb);
				myArray.add(itemObject);
				System.out.println(itemObject.toString());
			}
		}catch(SQLException e){
			System.out.println(e.toString());
			exec.closeConnection();
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("RankData", myArray);
		out.print(jsonObject);
		exec.closeConnection();
		
		out.flush();
		out.close();
	}
}

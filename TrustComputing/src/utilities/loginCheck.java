package utilities;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.UserManager;
import database.StringParser;
/**
 * @uuthor: zzk
 * @date: 2012/11/22
 * @description: This servlet is written to handle requests from terminal program
 * 				 and broswers.
 */

public class loginCheck extends HttpServlet {

	private static final long serialVersionUID = -5063631718637386751L;

	/**
	 * Constructor of the object.
	 */
	public loginCheck() {
		super();
	}

	/**
	 * @description This method is called to validate when terminal program trying to connect server.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String strResult[] = {"nothing", "invalidlogin", "alreadylogin", "successlogin"};
		/* Decode the query string */
		String queryString = request.getQueryString();

		StringParser myDecoder = new StringParser();
		String fields[] = queryString.split(";");
		String name = myDecoder.retriveData(fields[0], '=');
		String passwd = fields[1];
		UserManager manager = new UserManager();
		int iResult = manager.isExist(name, passwd, 0);//Default user type is normal (tye argument is 0)
		manager.closeConnection();
		
		out.print(strResult[iResult]); 
		out.flush();
		out.close();
	}

	/**
	 * @description This method is called to deal with login request from broswer page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPasswd");
		String userType = request.getParameter("userType");
		int iuserType = Integer.parseInt(userType);
		
		UserManager manager = new UserManager();
		int iRes = manager.isMember(userName, userPassword, iuserType);
		if(iRes == 1){
			//Change the login time of current user from older record firstly.
			String strDatetime = manager.changeLastLogin(userName, userPassword);
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("userType", userType);
			session.setAttribute("lastLogin", strDatetime);
			session.setMaxInactiveInterval(300);//keep session valid for 10 minutes;
			switch(iuserType)
			{
			case 0: //normal user
				out.print(0);
				break;
			case 1: //super user
			case 2: //manager
				out.print(1);
				break;
			}
		}
		else{
			if(iRes == -1){
				out.print(3);  //The code '3' implies that the platform is not safe.
			}
			out.print(2);
		}
		manager.closeConnection();
	}
}

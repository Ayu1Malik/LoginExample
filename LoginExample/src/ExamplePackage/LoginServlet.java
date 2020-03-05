package ExamplePackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {
	System.out.println("Hiii");
String url="jdbc:mysql://localhost:3306/LoginDB";
String user="root";
String password="adm@123";
Connection con=null;
PreparedStatement pstmt=null;
Statement stmt;
//response.getWriter().append("Served at:").append(request.getContextPath());
String uname=request.getParameter("user");
String pass=request.getParameter("password");
System.out.println("Hiii");
try{
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection(url,user,password);
	stmt=con.createStatement();
	ResultSet rs=stmt.executeQuery("select * from LoginDetails");
	while(rs.next())
	{
		if((rs.getString(1).equalsIgnoreCase(uname))&&(rs.getString(2).equals(pass)))
		{
			RequestDispatcher rd=request.getRequestDispatcher("Success.jsp");
			rd.forward(request, response);
			}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("Fail.jsp");
			rd.forward(request,response);
		}
	}
			
		}catch(Exception e){
			
		}
	
}
}

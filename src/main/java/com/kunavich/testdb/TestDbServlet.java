package com.kunavich.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?allowPublicKeyRetrieval=true&useSSL=false";
		String user = "hbstudent";
		String pass = "hbstudent";
		String driver ="com.mysql.cj.jdbc.Driver";
		try {
			PrintWriter out = response.getWriter();
			out.println("Connecting to DB "+ jdbcUrl);
			Class.forName(driver);
			Connection conn= DriverManager.getConnection(jdbcUrl, user, pass);		
			out.println("Connecting successful! ");
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		
	}

}

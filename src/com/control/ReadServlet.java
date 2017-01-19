package com.control;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.util.Dao;
import com.util.TopicBean;

public class ReadServlet extends HttpServlet{
	private Connection conn;
	private Dao dao;
	
	public void init(){
		System.out.println("init function");
		String url="jdbc:mysql://localhost:3306/servletBBS?user=root&password=541412";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url);
			dao = new Dao(conn);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("Connection state : "+(conn != null));
		}
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		if(req.getParameter("id")==null)
		{
			RequestDispatcher dispatcher=req.getRequestDispatcher("Scan.jsp");
			dispatcher.forward(req, resp);
		}
		
		int id=Integer.parseInt(req.getParameter("id"));
		try {
			TopicBean topic = dao.getTopic(id);
			req.setAttribute("topic", topic);
			
			RequestDispatcher dispatcher=req.getRequestDispatcher("Read.jsp");
			dispatcher.forward(req, resp);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

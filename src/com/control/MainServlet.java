package com.control;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.util.Dao;
import com.util.TopicBean;

public class MainServlet extends HttpServlet{
	
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
		int page=1;
		if(req.getParameter("page") != null){
			page=Integer.parseInt(req.getParameter("page"));
		}
		if(page<0)
			page=1;
		
		int maxPages=dao.getPages();
		if(page > maxPages)
			page = maxPages;
	
		req.setAttribute("page", page);
		req.setAttribute("maxPages", maxPages);
		
		try{
			List<TopicBean> topics = dao.getTopic((page-1)*10, 10);
			req.setAttribute("topics", topics);
			RequestDispatcher dispatcher=req.getRequestDispatcher("Scan.jsp");
			dispatcher.forward(req, resp);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
}

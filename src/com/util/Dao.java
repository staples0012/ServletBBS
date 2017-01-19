package com.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class Dao {
	
	private Connection conn;
	public Dao(Connection conn)
	{
		this.conn=conn;
	}
	
	public boolean insertTopic(TopicBean topic) throws SQLException
	{
		Statement st=null;
		try{
		    st=(Statement) conn.createStatement();
			String sql="insert into topics values(0,'"+topic.getTitle()+"','"+topic.getContent()+"')";
			st.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(st != null)
				st.close();
		}
		return true;
	}
	
	public int getPages()
	{
		Statement st=null;
		String sql="select count(*) from topics";
		int pages=0;
		try{
			st=(Statement) conn.createStatement();
			ResultSet rs=(ResultSet) st.executeQuery(sql);
			rs.next();
			int lines=rs.getInt(1);
			pages=lines/10;
			if(lines % 10 !=0)
				pages++;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return pages;
	}
	
	public TopicBean getTopic(int id) throws SQLException
	{
		Statement st=null;
		TopicBean topic=null;
		try{
			st = (Statement) conn.createStatement();
			String sql="select * from topics where id="+id;
			ResultSet rs=(ResultSet) st.executeQuery(sql);
			while(rs.next())
			{
				topic = new TopicBean();
				topic.setId(rs.getInt(1));
				topic.setTitle(rs.getString(2));
				topic.setContent(rs.getString(3));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(st != null)
				st.close();
		}
		
		return topic;
	}
	
	public ArrayList<TopicBean> getTopic(int start,int count) throws SQLException
	{
		ArrayList<TopicBean> result=new ArrayList<TopicBean>();
		Statement st=null;
		
		try{
			st=(Statement) conn.createStatement();
			String sql="select * from topics order by id desc limit "+start+","+count;
			ResultSet rs=(ResultSet) st.executeQuery(sql);
			
			while(rs.next()){
				TopicBean topicBean=new TopicBean();
				topicBean.setId(rs.getInt(1));
				topicBean.setTitle(rs.getString(2));
				topicBean.setContent(rs.getString(3));
				
				result.add(topicBean);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(st != null)
				st.close();
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		Connection conn=null;
		String url="jdbc:mysql://localhost:3306/servletBBS?user=root&password=541412";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=(Connection) DriverManager.getConnection(url);
			
			Dao dao=new Dao(conn);
			List<TopicBean> list=dao.getTopic(0,3);
			
			System.out.println(list.size());
			for(TopicBean bean:list)
			{
				System.out.println(bean.getId()+"  "+bean.getTitle()+"  "+bean.getContent());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		System.out.println("done.");
	}
}

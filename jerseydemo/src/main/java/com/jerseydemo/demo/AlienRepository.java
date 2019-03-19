package com.jerseydemo.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AlienRepository {
	Connection conn=null;
	public AlienRepository() {
		String url="jdbc:mysql://127.0.0.1:3306/mydb?autoReconnect=true&useSSL=false";
		String uname="root";
		String pass="mysqlserver";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,uname,pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Alien> getAliens(){
		List<Alien> aliens=new ArrayList<>();
		String query="select * from alien";
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Alien a=new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				
				aliens.add(a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aliens;
	}
	
	public Alien getAlien(int id) {
		String query="select * from alien where id="+id;
		Alien a=new Alien();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			if(rs.next())
			{
				
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	public void create(Alien a) {
		String query="insert into alien values (?,?,?)";
		
		try {
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setInt(1, a.getId());
			pst.setString(2, a.getName());
			pst.setInt(3, a.getPoints());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(Alien a) {
		String query="update alien set name=?, points=? where id=?";
		
		try {
			PreparedStatement pst=conn.prepareStatement(query);
			
			pst.setString(1, a.getName());
			pst.setInt(2, a.getPoints());
			pst.setInt(3, a.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		String query="delete from alien where id=?";
		
		try {
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

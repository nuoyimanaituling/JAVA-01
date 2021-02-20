package com.example.demo.util;

import com.example.demo.Student;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBcon {


	public static void main(String[] args) throws SQLException {
//	    Connection connection =	DBcon.GetCon();
	    Connection connection =DBcon.getConByHikariDataSource();
	    //查询
		String sql = "select * from student";
		ResultSet resultSet = connection.createStatement().executeQuery(sql);
		while (resultSet.next()) {
			System.out.println(resultSet.getString("id")+"___________"+resultSet.getString("name"));
		}
//		deletestudent("1");
//		addStudent(new Student(1,"熊一"));
		updateWf(new Student(2,"xzw"));

	}
	public static void deletestudent(String id) throws SQLException {
		Connection con=DBcon.getConByHikariDataSource();
		PreparedStatement p=null;
		String sql="delete from student where id=?";
		try {
			p=con.prepareStatement(sql);
			p.setString(1, id);
			p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBcon.Close(p, con);


	}

	public static void addStudent(Student wf) throws SQLException {
		Connection con=DBcon.getConByHikariDataSource();
		PreparedStatement p = null;
		String sql="insert into student values(?,?)";
		try {
			p=con.prepareStatement(sql);
			p.setInt(1, wf.getId());
			p.setString(2, wf.getName());
			p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBcon.Close(p, con);

	}

	public static void updateWf(Student wf) throws SQLException {
		Connection con=DBcon.getConByHikariDataSource();
		PreparedStatement p = null;
		String sql="update student set name=? where id=?";
		System.out.println(wf.getId()+wf.getName());
		try {
			p=con.prepareStatement(sql);
			p.setString(1, wf.getName());
			p.setInt(2, wf.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBcon.Close(p, con);
	}
















	public static Connection GetCon() {
		Connection con=null;
		String driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/test1?useSSlL=true&characterEncoding=utf-8";
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, "root", "123456");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public static Connection getConByHikariDataSource() throws SQLException {
	  Connection con =getHikariDataSource().getConnection();
		return con;
	}
	public static void Close(ResultSet rs,PreparedStatement prest,Connection con)
	{
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(prest!=null)
		{
			try {
				prest.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void Close(PreparedStatement prest,Connection con) {

		if(prest!=null)
		{
			try {
				prest.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static  DataSource getHikariDataSource() {
		HikariConfig configuration = new HikariConfig();
		configuration.setJdbcUrl("jdbc:mysql://localhost:3306/test1?useSSlL=true&characterEncoding=utf-8");
		configuration.setUsername("root");
		configuration.setPassword("123456");
		return new HikariDataSource(configuration);
	}

}

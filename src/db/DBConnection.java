package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbs.BBSDto;

public class DBConnection {
	public static final String className = "com.mysql.jdbc.Driver";
	public static final String address = "jdbc:mysql://localhost:3306/hospital?useUnicode=yes&characterEncoding=UTF-8";
	public static final String user = "admin";
	public static final String password = "secret";
	
	public static void initConnect() {
		try {
			Class.forName(className);
			
			System.out.println("Driver Loading Success!!");
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(address, user, password);
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement psmt, ResultSet rs) {		
		try {
			if(conn != null)
				conn.close();
			if(psmt != null)
				psmt.close();
			if(rs != null)
				rs.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, PreparedStatement psmt) {		
		try {
			if(conn != null)
				conn.close();
			if(psmt != null)
				psmt.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}	
	
	public static boolean executeUpdates(String sql, List<Object> queryList) {
		
		Connection conn = null;
		PreparedStatement psmt = null;		
		
		int count = 0;
		
		try {
			conn = getConnection();
			
			psmt = conn.prepareStatement(sql);
			System.out.println("1/6 통과");
			int i = 1;
			for (Object query : queryList) {
				if (query instanceof String) {
					psmt.setString(i, (String) query);
				} else if (query instanceof Integer) {
					psmt.setInt(i, (Integer) query);
				}
				i++;
			}
			System.out.println("2/6 통과");
			
			System.out.println(psmt.toString());
			count = psmt.executeUpdate();
			System.out.println("3/6 통과");
			
		} catch (SQLException e) {			
			System.out.println(e.getMessage());
		} finally{
			close(conn, psmt);
			System.out.println("6/6 통과");
		}
				
		return count>0?true:false;
	}
	
	public static boolean executeUpdate(String sql, Object query) {
		System.out.println("query입니다");
		
		Connection conn = null;
		PreparedStatement psmt = null;		
		
		int count = 0;
		
		try {
			conn = getConnection();
			
			psmt = conn.prepareStatement(sql);
			
			if (query instanceof String) {
				psmt.setString(1, (String) query);
			} else if (query instanceof Integer) {
				psmt.setInt(1, (Integer) query);
			}
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {			
			System.out.println(e.getMessage());
		} finally{
			close(conn, psmt);
		}
				
		return count>0?true:false;
	}

}

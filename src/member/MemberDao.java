package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;

public class MemberDao implements MemberDaoImpl {
	private static MemberDao single = null;
	
	private MemberDao() {
		DBConnection.initConnect();
	}
	
	public static MemberDao getInstance() {
		if (single == null) {
			single = new MemberDao();
		}
		
		return single;
	}

	@Override
	public MemberDto login(MemberDto dto) {
		
        MemberDto mem = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT EMAIL, NAME, PHONE, AUTH "
				+ " FROM MEMBER "
				+ " WHERE EMAIL=? AND PW=? ";
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getEmail());
			psmt.setString(2, dto.getPw());			
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				String email = rs.getString(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String auth = rs.getString(4);
				
				mem = new MemberDto(email, null, name, phone, auth);	
			}	
			if (mem == null) {
				
				System.out.println("login Fail");
				
				return null;
			}
			
		} catch (SQLException e) {		
			e.printStackTrace();			
		} finally{
			DBConnection.close(conn, psmt, rs);			
		}
		
		return mem;
	}
	
	public boolean addMember(MemberDto dto) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
				
		String sql = " INSERT INTO MEMBER "
				+ "(EMAIL, PW, NAME, PHONE, AUTH) "
				+ "VALUES(?, ?, ?, ?, ?) ";
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getEmail());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getPhone());
			psmt.setString(5, dto.getAuth());
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("addMember Fail");					
		} finally{
			DBConnection.close(conn, psmt, null);			
		}
		
		return count>0?true:false;
	}
}

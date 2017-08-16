package bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class BBSDao implements BBSDaoImpl 
{
	private static BBSDao single = null;
	
	private BBSDao(){}
	
	public static BBSDao getInstance() {
		if(single == null){
			single = new BBSDao();
		}
		return single;
	}
	
	//게시물 갯수 가져오기
	@Override
	public int getTotalArticle(String s_type, String q) {
		String columnSql = "COUNT(SEQ)";
		String sql = "SELECT "+columnSql+" FROM BBS"
					+ " WHERE "+s_type+" LIKE '%"+q+"%' ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int total_article = 0;
				
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				int i = 1;
				total_article = rs.getInt(i++);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, psmt, rs);
		}
		
		return total_article;
	}
	
	//쿼리 게시물 갯수 가져오기
		@Override
		public int getTotalArticle() {
			String columnSql = "COUNT(SEQ)";
			String sql = "SELECT "+columnSql+" FROM BBS";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			int total_article = 0;
					
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				
				while (rs.next()) {
					int i = 1;
					total_article = rs.getInt(i++);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, psmt, rs);
			}
			
			return total_article;
		}
		
	//글쓰기
	public boolean writeBbs(BBSDto bbs)
	{		
		String sql = "INSERT INTO BBS(category,user_id,title,content,status,count,ancestor) "
				+ " VALUES(?, ?, ?, ?, ?, 0, "
				+ "("
					+ "(select * from (select ifnull(max(seq),0)+1 from BBS) as a)"
				+ ")) ";

		System.out.println("BBSDao: "+bbs.toString());
		
		List<Object> queryList = new ArrayList<>();
		
		queryList.add(bbs.getCategory());
		queryList.add(bbs.getUser_id());
		queryList.add(bbs.getTitle());
		queryList.add(bbs.getContent());
		queryList.add(bbs.getStatus());
		
		return DBConnection.executeUpdate(sql, queryList);
	}

	//리스트 가져오기
	@Override
	public List<BBSDto> getBbsList(int cur_page) {
		//페이징 계산
		PaginationBeans paging = PaginationBeans.getInstance();
		int limit = paging.article_limit;
		
		int startNum = limit*(cur_page - 1);
		int endNum = limit*cur_page;
						
		String columnSql = "seq,category,user_id,title,content,status,count,create_at,updated_at,ancestor,reply";
		String sql = " SELECT "+columnSql+" FROM BBS "
					+ " WHERE category = 'notice' "
					+ " ORDER BY ancestor desc,reply asc "
					+ " limit " + startNum + "," + endNum;
		
		List<BBSDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsList Success");
			
			while(rs.next())
			{
				int i = 1;		
				BBSDto bbs = new BBSDto
				(
					rs.getInt(i++), 	//seq
					rs.getString(i++), 	//category
					rs.getString(i++), 	//user_id
					rs.getString(i++), 	//title
					rs.getString(i++), 	//content
					rs.getString(i++), 	//status
					rs.getInt(i++), 	//count
					rs.getString(i++), 	//create_at
					rs.getString(i++), 	//updated_at
					rs.getInt(i++), 	//ancestor
					rs.getString(i++) 	//reply
				);
				list.add(bbs);
			}		
			System.out.println("5/6 getBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getBbsList fail");
		} finally{
			DBConnection.close(conn, psmt, rs);
			System.out.println("6/6 getBbsList Success");
		}
		
		return list;
	}
	
	//쿼리 리스트 가져오기
	@Override
	public List<BBSDto> getBbsList(int cur_page, String s_type, String q) {
		//페이징 계산
		PaginationBeans paging = PaginationBeans.getInstance();
		int limit = paging.article_limit;
		
		int startNum = limit*(cur_page - 1);
		int endNum = limit*cur_page;
						
		String columnSql = "seq,category,user_id,title,content,status,count,create_at,updated_at,ancestor,reply";
		String sql = " SELECT "+columnSql+" FROM BBS "
					+ " WHERE category = 'notice' "
					+ " AND "+s_type+" LIKE '%"+q+"%' "
					+ " ORDER BY ancestor desc,reply asc "
					+ " limit " + startNum + "," + endNum;
		
		System.out.println(sql);
		
		List<BBSDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsList Success");
			
			while(rs.next())
			{
				int i = 1;		
				BBSDto bbs = new BBSDto
				(
					rs.getInt(i++), 	//seq
					rs.getString(i++), 	//category
					rs.getString(i++), 	//user_id
					rs.getString(i++), 	//title
					rs.getString(i++), 	//content
					rs.getString(i++), 	//status
					rs.getInt(i++), 	//count
					rs.getString(i++), 	//create_at
					rs.getString(i++), 	//updated_at
					rs.getInt(i++), 	//ancestor
					rs.getString(i++) 	//reply
				);
				list.add(bbs);
			}		
			System.out.println("5/6 getBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getBbsList fail");
		} finally{
			DBConnection.close(conn, psmt, rs);
			System.out.println("6/6 getBbsList Success");
		}
		
		return list;
	}

	@Override
	public BBSDto getBbs(int seq) {		
		BBSDto bbs = null;
		
		String sql = "SELECT * FROM BBS WHERE SEQ = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			
			rs.next();
			int i = 1;
			bbs = new BBSDto
			(
				rs.getInt(i++), 	//seq
				rs.getString(i++), 	//category
				rs.getString(i++), 	//user_id
				rs.getString(i++), 	//title
				rs.getString(i++), 	//content
				rs.getString(i++), 	//status
				rs.getInt(i++), 	//count
				rs.getString(i++), 	//create_at
				rs.getString(i++), 	//updated_at
				rs.getInt(i++), 	//ancestor
				rs.getString(i++) 	//reply
			);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, psmt, rs);
		}		
		return bbs;
	}

	@Override
	public void updateBbs(int seq) {
		String sql = " UPDATE INTO BBS( "
				+ " WHERE SEQ = ? ";
		
	}

	@Override
	public void deleteBbs(int seq) {
		String sql = " DELETE FROM BBS "
				+ " WHERE SEQ = ? ";
		
		Object query = seq;
		
		DBConnection.executeUpdate(sql, query);
		
	}	
}
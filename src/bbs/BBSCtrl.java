package bbs;

import java.util.List;

public class BBSCtrl 
{
	//A_BbsServiceImpl 연결
	BBSServiceImpl bbsSer = new BBSService();
	
	//총 페이지 수를 가져온다
	public int getTotalArticle() {
		return bbsSer.getTotalArticle();
	}
	public int getTotalArticle(String s_type, String q) {
		return bbsSer.getTotalArticle(s_type,q);
	}
	
	//글쓰기
	public boolean writeBbs(String category, String user_id, String title, String content, String status, int ancestor)
	{
		BBSDto bbs = new BBSDto(category, user_id, title, content, status, ancestor);
		
		System.out.println("BBSCtrl: "+bbs.toString());
		
		return bbsSer.writeBbs(bbs);
	}
	
	//리스트를 가져온다
	public List<BBSDto> getBbsList(int cur_page) {
		return bbsSer.getBbsList(cur_page);
	}
	
	//쿼리 리스트를 가져온다
		public List<BBSDto> getBbsList(int cur_page, String s_type, String q) {
			return bbsSer.getBbsList(cur_page, s_type, q);
	}
	
	//단일 게시물을 가져온다
	public BBSDto getBbs(int seq) {
		return bbsSer.getBbs(seq);
	}
	public void updateBbs(int seq, String title, String content) {
		BBSDto dto = new BBSDto(seq, title, content);
		bbsSer.updateBbs(dto);
		
	}
	public void deleteBbs(int seq) {
		bbsSer.deleteBbs(seq);
		
	}
	public void replyBbs(int seq, String category, String user_id, String title, String content, String status, int ancestor, String reply) {
		BBSDto dto = new BBSDto(seq, category, user_id, title, content, status, ancestor, reply);
		
		bbsSer.replyBbs(dto);
		
	}
}

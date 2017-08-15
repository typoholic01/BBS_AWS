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
	public boolean writeBbs(String category, String user_id, String title, String content, String status, int parent)
	{
		BBSDto bbs = new BBSDto(category, user_id, title, content, status, parent);
		
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
}

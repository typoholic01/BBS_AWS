package bbs;

public class PaginationBeans {	
	private static PaginationBeans single = null;

	private int total_article;				//총 게시물
	private int cur_page;					//현재 페이지
	private int start_page;					//리스트 시작 번호
	private int end_page;					//리스트 종료 번호
	public final int article_limit = 10;	//한 페이지 당 게시물 제한
	public final int page_limit = 10;		//한 리스트 당 페이지 수 제한

	private PaginationBeans() {
		
	}
	
	public static PaginationBeans getInstance() {
		if (single == null) {
			single = new PaginationBeans();
		}
		return single;
	}

	public int getCur_page() {
		return cur_page;
	}

	public void setCur_page(int cur_page) {
		this.cur_page = cur_page;
	}

	public int getTotal_article() {
		return total_article;
	}

	public void setTotal_article(int total_page) {
		this.total_article = total_page;
	}

	public int getStart_page() {
		return start_page;
	}

	public void setStart_page(int start_page) {
		this.start_page = start_page;
	}

	public int getEnd_page() {
		return end_page;
	}

	public void setEnd_page(int end_page) {
		this.end_page = end_page;
	}

}

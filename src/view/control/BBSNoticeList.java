package view.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.BBSDto;
import bbs.PaginationBeans;
import singleton.Delegate;

@WebServlet("/bbs/notice/list")
public class BBSNoticeList extends HttpServlet {
	private static final long serialVersionUID = 558959584905115319L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Delegate d = Delegate.getInstance();		

		//페이징 상수 초기화
		PaginationBeans paging = PaginationBeans.getInstance();

		int cur_page = 0;
		if (req.getParameter("p") != null) {
			cur_page = Integer.parseInt(req.getParameter("p"));				
		}
		
		if (req.getParameter("q") != null && req.getParameter("s_type") != null) {
			String q = req.getParameter("q");
			String s_type = req.getParameter("s_type");
			paging.setTotal_article(d.BBSCtrl.getTotalArticle(s_type, q));	//최대 게시물 수			
		} else {
			paging.setTotal_article(d.BBSCtrl.getTotalArticle());	//최대 게시물 수			
		}
		
		
		//잘못된 페이지 번호가 넘어왔을때 교정한다
		if (cur_page <= 0) {
			cur_page = 1;
		} else if (cur_page > paging.getTotal_article()) {
			cur_page = paging.getTotal_article();
		}
		
		//리스트 시작 번호를 세팅한다
		int start_page = cur_page-(cur_page-1)%10;
		int end_page = start_page+paging.page_limit - 1;
		
		//end_page의 한계를 설정한다
		if (end_page*paging.article_limit > paging.getTotal_article()) {
			// 1의 자리수를 빼고 더한다
			end_page = paging.getTotal_article()/paging.article_limit;				
			// 1의 자리수가 1이라도 있으면 페이지를 추가한다
			end_page += (paging.getTotal_article()%paging.article_limit > 0)?1:0;	
		}
		
		//번호를 세팅한다
		paging.setCur_page(cur_page);
		paging.setStart_page(start_page);
		paging.setEnd_page(end_page);

		List<BBSDto> bbsList = null;
		//쿼리 저장하기
		String queryURL = "";
		if (req.getParameter("q") != null && req.getParameter("s_type") != null) {
			String q = req.getParameter("q");
			String s_type = req.getParameter("s_type");
			queryURL = "&s_type="+s_type+"&q="+q;
			
			//쿼리 리스트 가져오기
			bbsList = d.BBSCtrl.getBbsList(cur_page, s_type, q);
		} else {			
			bbsList = d.BBSCtrl.getBbsList(cur_page);				//리스트 가져오기
		}		
		
		req.setAttribute("paging", paging);				//페이징 세팅
		req.setAttribute("bbsList", bbsList);			//리스트 세팅
		req.setAttribute("queryURL", queryURL);			//쿼리 세팅
		
		//보내기
		dispatch("/board/bbs_notice_list.jsp", req, resp);			
		
	}
	
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
	}

}

package view.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.BBSDto;
import bbs.PaginationBeans;
import member.MemberDto;
import singleton.Delegate;

@WebServlet("/bbs/*")
/*
 * mapping url pattern
 *  "/*" : 모든 요청
 * "*.do" : 확장자가 do인 요청
 * "/board/*" : /board/로 시작하는 요청
 */
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 3286113481968426199L;
	private Delegate d = Delegate.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath();				//프로젝트 url을 가져온다
		String url = req.getRequestURI();						//전체 url을 가져온다
		String command = url.substring(contextPath.length());	//전체 url에서 프로젝트 url만큼 자른다
        
		req.setCharacterEncoding("utf-8");						//utf-8 설정
		resp.setContentType("text/html; charset=UTF-8");
		
		String category,user_id,title,content,status;		//param
		int seq;
		BBSDto bbs = null;
		
		switch (command) {
		case "/bbs/notice/list":
			
			list(req,resp);			
			
			break;
			
		case "/bbs/notice/write":
			
			dispatch("/board/bbs_notice_write.jsp", req, resp);	
			
			break;
			
		case "/bbs/notice/writeAf":
			
			user_id = req.getParameter("user_id");
			category = req.getParameter("category");
			title = req.getParameter("title");
			content = req.getParameter("content");
			status = "published";
			
			d.BBSCtrl.writeBbs(category, user_id, title, content, status, 0);
			
			dispatch("/bbs/notice/list", req, resp);		
			break;
			
		case "/bbs/notice/detail":	
			//seq를 받아온다
			seq = Integer.parseInt(req.getParameter("seq"));
			
			//게시물을 받아온다
			bbs = d.BBSCtrl.getBbs(seq);
			
			//req에 넣는다
			req.setAttribute("bbs", bbs);
			
			//보내기
			dispatch("/board/bbs_notice_detail.jsp", req, resp);	
			break;
			
		case "/bbs/notice/modify":
			//seq를 받아온다
			seq = Integer.parseInt(req.getParameter("seq"));
			
			//게시물을 받아온다
			bbs = d.BBSCtrl.getBbs(seq);
			
			//req에 넣는다
			req.setAttribute("bbs", bbs);
			
			//보내기
			dispatch("/board/bbs_notice_update.jsp", req, resp);
			
			break;
			
		case "/bbs/notice/modifyAf":
			//seq를 받아온다
			seq = Integer.parseInt(req.getParameter("seq"));
			title = req.getParameter("title");
			content = req.getParameter("content");
			
			d.BBSCtrl.updateBbs(seq, title, content);
			
			//보내기
			dispatch("/bbs/notice/detail", req, resp);
			
			break;
			
		case "/bbs/notice/delete":
			//seq를 받아온다
			seq = Integer.parseInt(req.getParameter("seq"));
			
			d.BBSCtrl.deleteBbs(seq);
			
			//보내기
			dispatch("/bbs/notice/list", req, resp);
			
			break;
			
		case "/bbs/notice/reply":			
			//보내기
			dispatch("/board/bbs_notice_reply.jsp", req, resp);
			
			break;
		case "/bbs/notice/replyAf":	
			//seq를 받아온다
			seq = Integer.parseInt(req.getParameter("seq"));
			user_id = req.getParameter("user_id");
			title = req.getParameter("title");
			content = req.getParameter("content");
			
			//답글을 입력한다
			d.BBSCtrl.replyBbs(seq, "notice", user_id, title, content, "general");
			
			//목록으로 돌아간다
			dispatch("/bbs/notice/list", req, resp);
			
			break;
			
		default:
			break;
		}
		
		
	}
	
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
	}
	
	//리스트 메소드
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
}

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
/*		case "/bbs/notice/write":
			
			dispatch("/board/bbs_notice_write.jsp", req, resp);				
			break;*/
			
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
			
		case "/bbs/notice/modifyAf":
			//seq를 받아온다
			seq = Integer.parseInt(req.getParameter("seq"));
			title = req.getParameter("title");
			content = req.getParameter("content");
			
			BBSDto dto = new BBSDto(title, content);
			
			d.BBSCtrl.updateBbs(seq,dto);
			
			//보내기
			dispatch("/board/bbs_notice_detail.jsp", req, resp);
			
		case "/bbs/notice/delete":
			//seq를 받아온다
			seq = Integer.parseInt(req.getParameter("seq"));
			
			d.BBSCtrl.deleteBbs(seq);
			
			//보내기
			dispatch("/board/bbs_notice_list.jsp", req, resp);
			
		default:
			break;
		}
		
		
	}
	
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO 뒤로가기를 두번해야 하는 문제. 선생님께 여쭤볼 것
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
	}
}

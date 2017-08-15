package view.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDto;
import singleton.Delegate;

@WebServlet("/account/*")
/*
 * mapping url pattern
 *  "/*" : 모든 요청
 * "*.do" : 확장자가 do인 요청
 * "/board/*" : /board/로 시작하는 요청
 */
public class MemberFrontController extends HttpServlet {
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
		
		String email,pw,name,phone,auth;		//param
		
		switch (command) {
		case "/account/login":
			dispatch("/member/login.jsp", req, resp);				
			break;
			
		case "/account/loginAf":
			email = req.getParameter("email");
			pw = req.getParameter("pw");
			
			MemberDto mem = new MemberDto();
			
			mem = d.memCtrl.login(email, pw);
			
			req.setAttribute("login", mem);			
									
			dispatch("/main.jsp", req, resp);		
			break;
			
		case "/account/register":
			dispatch("/member/register.jsp", req, resp);		
			break;
			
		case "/account/registerAf":
			email = req.getParameter("email");
			pw = req.getParameter("pw");
			auth = "general";
			
			d.memCtrl.addMember(email, pw, null, null, auth);
			
			dispatch("/member/login.jsp", req, resp);		
			break;
			
		default:
			break;
		}
		
		
	}
	
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
	}
}

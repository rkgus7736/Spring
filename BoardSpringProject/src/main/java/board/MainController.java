package board;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.MemberDTO;
import board.service.MemberService;
@Controller
public class MainController {
	private MemberService memberService;
	
	public MainController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	@RequestMapping("loginView.do")
	public String loginView() {
		return "login";
	}
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return main();
	}
	@RequestMapping("login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String id = request.getParameter("id");
		String pass =request.getParameter("pass");
		MemberDTO dto = memberService.login(id,pass);
		//HttpSession session = request.getSession();
		if(dto != null) {
			session.setAttribute("login", true);
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
			session.setAttribute("grade", dto.getGrade());
			session.setMaxInactiveInterval(10);
			
			return "main";
		}else {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('아이디 비번을 확인하세요');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	@RequestMapping("/updateMemberView.do")
	public String updateMemberView(HttpServletRequest request , HttpSession session) {
	//아이디는 session에 있는걸 받아오면 됨
		String id = (String) session.getAttribute("id");
		//세션시간을 30분으로 설정했기때문에 세션정보가 유효한지 체크
		if(id == null)
			return "login";
		
		//회원정보조회
		MemberDTO dto = memberService.selectMember(id);
		//dto에서 받아와서 "dto"를 만들겠다
		request.setAttribute("dto", dto);
		
		return "member_update_view";
	}
	
	@RequestMapping("/updateMemberAction.do")
	public String updateMemberAction(HttpServletRequest request, HttpServletResponse response) {
		//아이디를 session으로 받지않아도 되지만,session으로 받으면 수정하기 전에 세션이 유효한지 확인 가능 
		//아이디 값을 session으로 받아올거면 HttpServletResponse도 불러와줘야함
		String id= (String) request.getSession().getAttribute("id");
		String pass= request.getParameter("pass");
		String name= request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		if(id == null) {
			//세션이 만료된 경우
			try {
				//글자 인코딩
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().write("<script>alert('세션이 만료되었습니다."
						+ " 다시 로그인해 주세요');"
						+ "location.href='login.do';</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		memberService.updateMember(new MemberDTO(id, pass, name, age));
		
		return "main";
		
	}
	
	@RequestMapping("/memberAdminMain.do")
	public String memberAdminMain(HttpServletRequest request) {
		List<MemberDTO> list = memberService.selectAllMember();
		request.setAttribute("list", list);
		return "member_manage_main";
	}
	
	@RequestMapping("/memberSearch.do")
	public String memberAdminSearch(HttpServletRequest request, HttpServletResponse response) {
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		System.out.println(kind + " "+search);
		List<MemberDTO> list = memberService.selectSearchMember(kind, search);
		response.setContentType("text/html;charset=utf-8");
		JSONArray array = new JSONArray(list);
		JSONObject obj = new JSONObject();
		obj.put("result",array);
		if(list.size()>0) {
			obj.put("responseCode", 200); //성공
			obj.put("responseMessage", "SearchSuccess"); 
		}else { 
			obj.put("responseCode", 500); //실패
			obj.put("responseMessage", "SearchFailed"); 
		}
			
		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@RequestMapping("/memberAjaxUpdate.do")
	public String memberAjaxUpdate(HttpServletRequest request, HttpServletResponse response) {
		String id= request.getParameter("id");
		String name= request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String grade =request.getParameter("grade");
		
		MemberDTO dto = new MemberDTO(id, null, name, age, grade);
		int count = memberService.updateMemberAdmin(dto);
			try {
				if(count == 1)
					response.getWriter().write("true");
				else
					response.getWriter().write("false");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		return null;
		
	}
	@RequestMapping("/memberAjaxDelete.do")
	public String memberAjaxDelete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int count = memberService.deleteMemberAdmin(id);
		try {
			response.getWriter().write(count + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}


	

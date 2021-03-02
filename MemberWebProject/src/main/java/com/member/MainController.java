package com.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.MemberDTO;
import service.MemberService;

@Controller
public class MainController {
	private MemberService service;

	public MainController(MemberService service) {
		super();
		this.service = service;
	}
	
	
	@RequestMapping("/")
	public String main() {
		return "member_manager";
	}
	
	@RequestMapping("/index.do")
	public String index(HttpServletResponse response) {
		List<MemberDTO> list = service.selectAllMember();
		JSONObject obj = new JSONObject();
			obj.put("result", new JSONArray(list));
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().write(obj.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		return "main";
	}
	
	@RequestMapping("/register.do")
	public String register(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		int grade = Integer.parseInt(request.getParameter("grade"));
		
		service.registerMember(new MemberDTO(id,pass,name,age,grade));
		
		return null;
	}
	
	@RequestMapping("/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int count = service.deleteMember(id);
			try {
				response.getWriter().write(count + "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return null;
	}
	
	@RequestMapping("/sendLog.do")
	public String sendLog(HttpServletRequest request, HttpServletResponse response) {
		String log_date = request.getParameter("log_date");
		int code_number = Integer.parseInt(request.getParameter("code_number"));
		String message = request.getParameter("message");
		System.out.println(log_date + " " + code_number + " " + message);
		int count = service.insertLog(log_date,code_number,message);
		System.out.println(count);
			try {
				response.getWriter().write("add count + "+count);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
	
}

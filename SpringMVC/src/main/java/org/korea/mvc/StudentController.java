package org.korea.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.korea.mvc.dto.StudentDTO;
import org.korea.mvc.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
	private StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
		System.out.println("StudentService 생성");
	}
	
	@RequestMapping("/student_manager.do")
	public String selectAllStudent(HttpServletRequest request,HttpSession session) {
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		request.setAttribute("list", list);
		return "student_manager";
		
	}
	
}

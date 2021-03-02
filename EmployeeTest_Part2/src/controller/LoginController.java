package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.EmployeeDTO;
import model.ModelAndView;
import service.EmployeeService;

public class LoginController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String sabun = request.getParameter("sabun");
		String name = request.getParameter("name");
		ModelAndView view = null;
		EmployeeDTO dto = EmployeeService.getInstance().login(sabun,name);
		
		if(dto != null) {
			request.getSession().setAttribute("dto", dto);
			if(dto.getPosition() > 3 && dto.getDepartment().equals("인사")) {
				ArrayList<EmployeeDTO> list = EmployeeService.getInstance().selectAllEmployee();
				ArrayList<String> position =  EmployeeService.getInstance().selectAllPositionList();
				request.getSession().setAttribute("position",position);
				request.getSession().setAttribute("list",list);
			}
			view = new ModelAndView("employee_manager.jsp", true);
		}else {
			try {
				response.getWriter().append("<script>alert('사번을 확인하세요.');"
							+"history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return view;
	}

}

package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.EmployeeDTO;
import model.ModelAndView;
import service.EmployeeService;

public class RefreshController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<EmployeeDTO> list = EmployeeService.getInstance().selectAllEmployee();
		ArrayList<String> position = EmployeeService.getInstance().selectAllPositionList();
		request.getSession().setAttribute("position", position);
		request.getSession().setAttribute("list", list);
		return new ModelAndView("employee_manager.jsp", true);
	}

}

package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dto.EmployeeDTO;
import model.ModelAndView;
import service.EmployeeService;

public class SearchController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		ArrayList<EmployeeDTO> list = EmployeeService.getInstance().selectSearchEmployee(kind,search);
		ArrayList<String> position =  EmployeeService.getInstance().selectAllPositionList();
		JSONArray array = new JSONArray(list);
		JSONArray posList = new JSONArray(position);
		JSONObject obj = new JSONObject();
		obj.put("list", list);
		obj.put("position", position);
		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

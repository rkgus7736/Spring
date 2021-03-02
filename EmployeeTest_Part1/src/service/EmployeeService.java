package service;

import java.util.ArrayList;

import dao.EmployeeDAO;
import dto.EmployeeDTO;

public class EmployeeService {
	private static EmployeeService instance = new EmployeeService();
	private EmployeeDAO dao;
	private EmployeeService() {
		dao = EmployeeDAO.getInstance();
	}
	
	public static EmployeeService getInstance() {
		if(instance==null)
			instance= new EmployeeService();
		return instance;
	}

	public EmployeeDTO login(String sabun, String name) {
		return dao.login(sabun, name);
	}

	public ArrayList<EmployeeDTO> selectAllEmployee() {
		return dao.selectAllEmployee();
	}

	public ArrayList<String> selectAllPositionList() {
		return dao.selectAllPositionList();
	}

	public ArrayList<EmployeeDTO> selectSearchEmployee(String kind, String search) {
		return dao.selectSearchEmployee(kind,search);
	}

	public int updateEmployee(EmployeeDTO employeeDTO) {
		return dao.updateEmployee(employeeDTO);
	}

	public int deleteEmployee(String eno) {
		return dao.deleteEmployee(eno);
	}

	public int insertEmployee(EmployeeDTO employeeDTO) {
		int count =	dao.insertEmployeeDTO(employeeDTO);
			if(count == 0 ) return 0;
		return dao.insertEmployeeSalary(employeeDTO);
	}
	
	
	
}











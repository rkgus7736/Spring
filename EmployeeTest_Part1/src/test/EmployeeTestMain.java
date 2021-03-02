package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import config.DBManager;
import dao.EmployeeDAO;
import dto.EmployeeDTO;

class employeeDAOTest {
	private static Connection conn = null;

	@BeforeAll
	static void setUp() {
		System.out.println("테스트 시작");
		Connection conn = DBManager.getInstance().getConn();
	}
	
	@DisplayName("사원 정보 삭제 테스트")
	@Test
	void testDeleteEmployee() {
		System.out.println("사원 정보 삭제 테스트 시작");
		String eno = "AA11";
		int count = EmployeeDAO.getInstance().deleteEmployee(eno);
		if(count == 0) {
			fail("삭제할 데이터가 없습니다.");
		}else {
			System.out.println(0);
		}
	}
	
	@DisplayName("사원 로그인 테스트")
	@Test
	void testLogin() {
		System.out.println("사원 로그인 테스트 시작");
		String sabun = "AA11";
		String name = "홍길동";
		EmployeeDTO dto = EmployeeDAO.getInstance().login(sabun, name);
		if(dto == null) {
			fail("해당하는 아이디의 사원이 없습니다.");
		}else {
			System.out.println(sabun + " " + name);
		}
		
	}
	
	@DisplayName("사원 검색 테스트")
	@Test
	void testSearchEmployeeList() {
		System.out.println("사원 검색 테스트 시작");
		String kind1 = "name";
		String kind2 = "eno";
		String search1 = "홍길동";
		String search2 = "A";
		
		ArrayList<EmployeeDTO> list1 = EmployeeDAO.getInstance().selectSearchEmployee(kind1, search1);
		ArrayList<EmployeeDTO> list2 = EmployeeDAO.getInstance().selectSearchEmployee(kind2, search2);
		
		if(!list1.isEmpty() && !list2.isEmpty()) {
			System.out.println("사원 검색 테스트 성공");
		}
		
		
	}
	
	@DisplayName("사원 정보 수정 테스트")
	@Test
	void testUpdateEmployee(){
		System.out.println("사원정보 수정 테스트 시작");
		String eno = "XD59";
		String name = "홍길동";
		String department = "영업";
		int position = 4;
		int salary = 6000;
		
		EmployeeDTO dto = new EmployeeDTO(eno, name, department, position, salary);
		int count = EmployeeDAO.getInstance().updateEmployee(dto);
		if(count != 0) {
			System.out.println("사원정보 수정 테스트 성공");
		}
		
	}
	
	@DisplayName("사원 연봉 정보 등록 테스트")
	@Test
	void testInsertSalaryEmployee(){
		System.out.println("사원 연봉 정보 등록 테스트 시작");
		String eno = "AA11";
		int salary = 3600;
		
		EmployeeDTO dto = new EmployeeDTO(eno, null, null, 0, salary);
		int count = EmployeeDAO.getInstance().insertEmployeeDTO(dto);
		int count2 = EmployeeDAO.getInstance().insertEmployeeSalary(dto);
		if(count ==0 && count2 ==0) {
			fail("등록에 실패하였습니다.");
		}else {
			
		}
		
	}
	
	@AfterAll
	public static void done() {
		System.out.println("테스트 종료");
		try {
			conn.rollback();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
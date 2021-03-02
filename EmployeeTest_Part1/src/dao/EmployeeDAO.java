package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DBManager;
import dto.EmployeeDTO;

public class EmployeeDAO {
	private static EmployeeDAO instance = new EmployeeDAO();
	private DBManager manager;
	private Connection conn;
	private EmployeeDAO() {
		manager = DBManager.getInstance();
		conn = manager.getConn();
	}

	public static EmployeeDAO getInstance() {
		if(instance == null)
			instance = new EmployeeDAO();
		return instance;
	}

	public EmployeeDTO login(String sabun, String name) {
		String sql = "select e.eno, e.name, e.department, e.position, es.salary from employee e , employee_salary es where e.eno = es.eno(+) and e.eno like ? and e.name like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeDTO dto=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sabun);
			pstmt.setString(2, name);
			System.out.println(sabun + " " + name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.getInstance().close(pstmt, rs);
		}
		
		
		return dto;
	}

	public ArrayList<EmployeeDTO> selectAllEmployee() {
		String sql = "select e.eno, e.name, e.department, e.position, es.salary from employee e , employee_salary es where e.eno = es.eno(+)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.getInstance().close(pstmt, rs);
		}
		
		return list;
	}

	public ArrayList<String> selectAllPositionList() {
		String sql = "select * from position_list";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.getInstance().close(pstmt, rs);
		}
		
		return list;
	}

	public ArrayList<EmployeeDTO> selectSearchEmployee(String kind, String search) {
		String sql = "select * from (select e.eno, e.name, e.department, e.position, es.salary from employee e , employee_salary es where e.eno = es.eno(+)) where ";
		sql += kind + " like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.getInstance().close(pstmt, rs);
		}
		
		return list;
	}

	public int updateEmployee(EmployeeDTO employeeDTO) {
		String sql1 = "update employee set name=?, position=?, department=? where eno=?";
		String sql2 = "update employee_salary set salary=? where eno=?";
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, employeeDTO.getName());
			pstmt.setInt(2, employeeDTO.getPosition());
			pstmt.setString(3, employeeDTO.getDepartment());
			pstmt.setString(4, employeeDTO.getSabun());
			count = pstmt.executeUpdate();
			conn.commit();
			System.out.println(count);
			if(count == 0) return 0;
			pstmt.close();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, employeeDTO.getSalary());
			pstmt.setString(2, employeeDTO.getSabun());
			count = pstmt.executeUpdate();
			conn.commit();
			System.out.println(count);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.getInstance().close(pstmt, null);
		}
		
		return count;
	}

	public int deleteEmployee(String eno) {
		String sql1 = "delete from employee where eno=?";
		String sql2 = "delete from employee_salary where eno=?";
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, eno);
			count = pstmt.executeUpdate();
			conn.commit();
			System.out.println(count);
			if(count == 0) return 0;
			pstmt.close();
			pstmt.setString(1, eno);
			pstmt = conn.prepareStatement(sql2);
			count = pstmt.executeUpdate();
			conn.commit();
			System.out.println(count);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.getInstance().close(pstmt, null);
		}
		
		return count;
	}
	public int updateSalaryBottom5() {
		String sql = "update employee_salary set salary = salary + trunc(salary * 0.1) "
				+ "where eno in(select eno from(select rownum, eno, salary from (select * from employee_salary order by salary) where rownum <= 5))";
		int count = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			count = pstmt.executeUpdate();
			conn.commit();
			System.out.println(count);
			if(count == 0) return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.getInstance().close(pstmt, null);
		}
		return count;
	}

	public int insertEmployeeDTO(EmployeeDTO employeeDTO) {
		String sql = "insert into employee values(?,?,?,?)";
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, employeeDTO.getSabun());
			pstmt.setString(2, employeeDTO.getName());
			pstmt.setString(3, employeeDTO.getDepartment());
			pstmt.setInt(4, employeeDTO.getPosition());
			count = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.getInstance().close(pstmt, null);
		}
		return count;
	}

	public int insertEmployeeSalary(EmployeeDTO employeeDTO) {
		String sql = "insert into employee_salary values(?,?)";
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, employeeDTO.getSabun());
			pstmt.setInt(2, employeeDTO.getSalary());
			count = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			DBManager.getInstance().close(pstmt, null);
		}
		return count;
	}
}




















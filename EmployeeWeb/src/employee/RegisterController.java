package employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.EmployeeDAO;
import dto.EmployeeDTO;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String eno = request.getParameter("eno");
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		int position = Integer.parseInt(request.getParameter("position"));
		//데이터 검증 DAO로 보내기 전에 수행
	
			try {
				if(eno.length()!=4) throw new Exception("1001");
				EmployeeDAO.getInstance().insertEmployee(new EmployeeDTO(eno, name, department, position));
				ArrayList<EmployeeDTO> list = EmployeeDAO.getInstance().selectEmployeeAllList();
				JSONArray arr = new JSONArray(list);//json으로 변형
				JSONObject obj = new JSONObject();
				obj.put("result", arr);
				response.getWriter().write(obj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(1002);
			response.getWriter().write("입력한 데이터가 잘못되었습니다.");
		}catch (Exception e) {
			//response.sendError(sc, msg); //에러메세지가 톰캣에러페이지 기준으로 html로 세팅되서 넘어감
			//클라이언트 ajax 사용시 에러전달 방법
			response.setStatus(1001); //에러코드
			response.getWriter().write("사번은 4자리입니다."); //에러 메세지 전송
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
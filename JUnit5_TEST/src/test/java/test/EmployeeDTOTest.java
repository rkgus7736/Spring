package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.EmployeeDAO;

class EmployeeDTOTest { 
/*
 * fail : 작업 실패시 나타낼 행동, 
 * assertSame : == 연산, 
 * assertEquals : .equals 연산, 
 * assertTrue : 기본 값은 비어있으면 true, 비어있지 않으면 false -> 하지만 우리는 !를 붙여서 비어있을때 false가 뜨게 해줄것,
 */

	@BeforeAll //시작점
	static void testUp() {
		System.out.println("테스트 맨 처음 한번 수행할 일");
	}
	
	@AfterAll //종료점
	static void done() {
		System.out.println("테스트 맨 마지막에 한번 수행할 일");
	}
	
	@BeforeEach //각 테스트 메서드가 실행되기 전에 수행
	void init(){
		System.out.println("테스트 작업 전 수행");
	}
	
	@DisplayName("사원정보리스트 조회 테스트")
	@Test
	void testSelectEmployeeList() {
		String str = EmployeeDAO.getInstance().selectEmployeeList(0);
		if(str.length()==2)
			fail("데이터가 없습니다."); //작업 실패시 나타낼 행동
		else
			System.out.println(str);
	}
	
	@DisplayName("빈리스트 확인 테스트")
	@Test
	void testEmptyEmployeeList() {
		//기본 값은 비어있으면 true, 비어있지 않으면 false -> 하지만 우리는 !를 붙여서 비어있을때 false가 뜨게 해줄것
		assertTrue(!EmployeeDAO.getInstance().selectEmployeeAllList().isEmpty());
	}
	
	@DisplayName("하위연봉 5명 조회 테스트")
	@Test
	void testSelectBottem5Salary() {
		String str = EmployeeDAO.getInstance().selectBottom5Salary();
		if(str.length()<5)
			fail("데이터가 적습니다."); 
		else
			System.out.println(str);
	}
	
	@DisplayName("사원정보 하나 조회")
	@Test
	void testselectEmployee() {
	//assertSame : == 연산
		int n = 10;
		assertSame(n,10); 
	//assertEquals : .equals 연산
		//EmployeeDTO dto = new EmployeeDTO("TQ98", "강병헌", "영업", "과장", 6200, 4);
		//assertEquals(dto, EmployeeDAO.getInstance().selectEmployee("TQ98"));
		
	}
	
	@AfterEach // 각 테스트 메서드가 실행이 끝난 후에 수행
	void end() {
		System.out.println("테스트 작업 후 실행");
	}
	
	
	
}

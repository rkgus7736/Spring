package com.student;

import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) //jUnit test를 사용하려면 선언해줘야함 
@SpringBootTest
class StudentWebProjectApplicationTests {

	@Autowired
	StudentMapper mapper;
	
//	@BeforeAll
//	static void start() {
//			//static void일 경우 따로 선언을 해줘야한다.
//		StudentWebProjectApplicationTests t = new StudentWebProjectApplicationTests();
//			//제대로 나오는지 확인하기
//		System.out.println(t.mapper.selectAllStudent().toString());
//	}
	
	@Test
	void contextLoads() {
		//제대로 나오는지 확인하기
		System.out.println(mapper.selectAllStudent());
	}
	
	@DisplayName("학생정보 검색 테스트")
	@Test
	public void searchTest() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mode", "name");
		map.put("search", "수");
	//두가지 방법 사용 가능
		assertNotEquals("테스트 실패", 0, mapper.selectStudentMode(map));
	//	if(0 == mapper.selectStudentMode(map).size());
	//		fail("학생정보 검색 테스트 실패");

	}

}

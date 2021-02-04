package com.student;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) //jUnit test를 사용하려면 선언해줘야함 
@SpringBootTest
class TestDao {
	@Autowired
	StudentMapper mapper;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void test() {
		//제대로 나오는지 확인하기
				System.out.println(mapper.selectAllStudent());
	}

}

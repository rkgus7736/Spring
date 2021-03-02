package com.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mapper.MemberMapper;

@SpringBootTest
class MemberWebProjectApplicationTests {
	
	@Autowired
	MemberMapper mapper;
	@Test
	void contextLoads() {
	}

}

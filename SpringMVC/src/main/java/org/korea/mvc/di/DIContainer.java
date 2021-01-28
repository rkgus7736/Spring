package org.korea.mvc.di;

import org.korea.mvc.dao.MemberDAO;

import org.springframework.context.annotation.Bean;

public class DIContainer {

	@Bean
	public MemberDAO memberdao() { 
		return new MemberDAO();
	}
}

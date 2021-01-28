package org.korea.mvc.service;

import org.korea.mvc.dao.MemberDAO;
import org.korea.mvc.dto.MemberDTO;

public class MemberService {
	private MemberDAO dao;
	
	public MemberService(MemberDAO dao) {
		super();
		this.dao = dao;
		System.out.println("MemberService 생성");
	}



	public MemberDTO login(String id, String pass) {
		return dao.login(id,pass);
	}
}

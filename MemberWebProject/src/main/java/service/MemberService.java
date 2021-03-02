package service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import dto.MemberDTO;
import mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;
	
	public MemberService(MemberMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public int deleteMember(String id) {
		return mapper.deleteMember(id);
	}

	public int registerMember(MemberDTO dto) {
		return mapper.registerMember(dto);
	}

	public int insertLog(String log_date, int code_number, String message) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("log_date", log_date);
		map.put("code_number", code_number);
		map.put("message", message);
		
		return mapper.insertLog(map);
	}
	
	public List<MemberDTO> selectAllMember() {
		return mapper.selectAllMember();
	}
	

}

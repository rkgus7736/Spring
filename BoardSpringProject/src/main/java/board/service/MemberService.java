package board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import board.dto.MemberDTO;
import board.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;
	
	public MemberService(MemberMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public MemberDTO login(String id, String pass) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pass", pass);
		return mapper.login(map);
	}

	public MemberDTO selectMember(String id) {
		return mapper.selectMember(id);
	}

	public int updateMember(MemberDTO dto) {
		return mapper.updateMember(dto); 
	}

	public List<MemberDTO> selectAllMember() {
		return mapper.selectAllMember();
	}

	public List<MemberDTO> selectSearchMember(String kind, String search) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("search", search);
		return mapper.selectSearchMember(map);
	}

	public int updateMemberAdmin(MemberDTO dto) {
		return mapper.updateMemberAdmin(dto);
	}

	public int deleteMemberAdmin(String id) {
		return mapper.deleteMemberAdmin(id);
	}

}

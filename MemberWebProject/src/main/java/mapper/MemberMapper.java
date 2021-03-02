package mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dto.MemberDTO;

@Mapper
public interface MemberMapper {
	
	int deleteMember(String id);

	int registerMember(MemberDTO dto);

	int insertLog(HashMap<String, Object> map);

	List<MemberDTO> selectAllMember();
	
}

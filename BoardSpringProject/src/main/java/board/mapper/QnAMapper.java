package board.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.QnaDTO;

@Mapper
public interface QnAMapper {

	List<QnaDTO> selectQnaList(HashMap<String, Object> map);
	int insertQnA(QnaDTO qnaDTO);
	List<QnaDTO> selectQnaAdminList(int pageNo);
	int selectCount();
	QnaDTO selectQna(int qno);
	int updateQnAStatus(int qno); //update라서 int로 바꿔줌 
	int updateResponse(HashMap<String, Object> map);
	
	

}

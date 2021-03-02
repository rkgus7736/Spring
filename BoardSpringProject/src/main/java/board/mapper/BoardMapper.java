  
package board.mapper;

import java.util.HashMap;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.BoardDTO;
import board.dto.CommentDTO;
import board.dto.FileDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> selectBoardList(int page);
	int selectCount();
	void addCount(int bno);
	BoardDTO selectBoard(int bno);
	List<CommentDTO> selectBoardComment(int bno);
	int insertBoardComment(CommentDTO dto);
	int updateCommentLike(int cno);
	int updateCommentHate(int cno);
	void addBoardLikeHate(HashMap<String, Object> map);
	int newBno();
	int insertBoard(BoardDTO dto);
	void insertFileList(FileDTO dto);
	List<FileDTO> selectFileList(int bno);
	
	
}
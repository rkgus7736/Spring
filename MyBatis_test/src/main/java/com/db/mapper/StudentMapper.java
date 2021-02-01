package com.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.db.StudentDTO;

@Mapper
public interface StudentMapper {
	//xml에 쓴 id값과 매개변수명을 맞춰주면 됨
	//매개변수가 여러개라면 hashmap으로 보내주면 되고
	//public + 리턴값 + xaml에서 쓴 id값
	public List<StudentDTO> selectAllStudent();
	public int insertStudent(StudentDTO dto);
	public int updateStudent(StudentDTO dto);
	public int deleteStudent(String sno);	
	
	
	
	
}

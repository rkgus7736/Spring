package org.korea.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.korea.mvc.dto.MemberDTO;

import config.DBManager;

public class MemberDAO {
	private DBManager manager;
	
	public MemberDAO(DBManager manager) {
		super();
		this.manager = manager;
	}

	public MemberDTO login(String id, String pass) {
		MemberDTO dto = null;
		String sql = "select * from member where id = ? and pass = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				dto = new MemberDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	
}

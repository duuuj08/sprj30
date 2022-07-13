package com.tech.sprj30.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.sprj30.db.DBCon;
import com.tech.sprj30.dto.BoardDto;

public class BoardDao {
	static boolean orderflag=true;

	public void getBoardList(Model model) throws Exception {
		// select 작업
		Connection con=DBCon.getConnection();
		
		Map<String, Object> map=model.asMap();
		HttpServletRequest request
			=(HttpServletRequest) map.get("request");
		String field=request.getParameter("bid");
		System.out.println("field : "+field);
		
		String sql="";
		if (orderflag) {
			sql="select bid,bsub,bcontent from board3 order by "+field+" desc";	
			orderflag=false;
		}else {
			sql="select bid,bsub,bcontent from board3 order by "+field+" asc";
			orderflag=true;
		}
		if (field==null) {
			sql="select bid,bsub,bcontent from board3";
		}
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BoardDto> list=new ArrayList<BoardDto>();
		pstmt=con.prepareStatement(sql);
		
		//쿼리 실행
		rs=pstmt.executeQuery();
		while (rs.next()) {
			//dto에 담기
			BoardDto dto=new BoardDto();
			dto.setBid(rs.getString(1)); //1은 칼럼번호 1번 = bid
			dto.setBsub(rs.getString(2));
			dto.setBcontent(rs.getString(3));
			
			//리스트에 담기
			list.add(dto);			
		}
		//리스트를 모델에 전달
		model.addAttribute("list",list);
		
		
		
	}

}

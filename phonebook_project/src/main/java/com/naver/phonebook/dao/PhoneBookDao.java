package com.naver.phonebook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.naver.phonebook.pdto.PhoneBookPDto;
import com.naver.phonebook.sdto.PhoneBookSDto;

@Repository
public class PhoneBookDao {
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"ora_user", "hong");
			} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
//	1번 가입회원 정보 가져오기.
	
	public ArrayList<PhoneBookSDto>  loginfindAll() throws SQLException {
		Connection conn = open();
		
		ArrayList<PhoneBookSDto> loginlist = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *        ");
		sql.append("  FROM SIGNUP   ");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		
		try (conn; pstmt; rs) { 
			while (rs.next()) {
				PhoneBookSDto sdto = new PhoneBookSDto(); 
			    sdto.setEmail(rs.getString("email"));
				sdto.setPw(rs.getString("pw"));
				sdto.setPhoneNumber(rs.getString("phoneNumber"));
				sdto.setNmname(rs.getString("nmname"));
				loginlist.add(sdto);
			 }
		}
		return loginlist;
	  }
//		1-1 회원가입추가
	public void addjoin(PhoneBookSDto n) throws Exception {
		Connection conn = open();
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO SIGNUP				");
		sql.append("VALUES (  ?						");
		sql.append(" 		, ?						");
		sql.append(" 		, ?						");
		sql.append(" 		, ?						");
		sql.append(") 								");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		try(conn;pstmt) {
			pstmt.setString(1, n.getEmail());
			pstmt.setString(2, n.getPw());
			pstmt.setString(3, n.getPhoneNumber());
			pstmt.setString(4, n.getNmname());
			pstmt.executeUpdate();
		}
		
	}
// 1-2 아이디 중복 비교
	
	public ArrayList<PhoneBookSDto> findid(String id) throws SQLException {
		Connection conn = open();
		
		ArrayList<PhoneBookSDto> loginlist = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT email       	");
		sql.append("  FROM SIGNUP 		");
		sql.append("  WHERE email = ?  ");
	
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
		try (conn; pstmt; rs) { 
				PhoneBookSDto sdto = new PhoneBookSDto(); 
			    sdto.setEmail(rs.getString("email"));
				loginlist.add(sdto);
			 }
		}
		System.out.println(loginlist);
		return loginlist;
	  }

//	회원가입 정보 가져오기
	public List<PhoneBookPDto> getAlls() throws Exception {
		Connection conn = open();
		List<PhoneBookPDto> memlist = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT	 a.NMNAME					");
		sql.append("		,a.email					");
		sql.append("		,a.PHONENUMBER				");
		sql.append("		,a.ADDRESS					");
		sql.append("		,a.GUBUNNUM					");
		sql.append("		,b.GUBUN					");
		sql.append("FROM 	PHONEMEMBER a				");
		sql.append("		,CATEGUBUN b				");
		sql.append("WHERE 	a.gubunnum = b.gubunnum		");

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
//		말기
		try(conn; pstmt; rs) {
			while(rs.next()) {
				PhoneBookPDto pdto = new PhoneBookPDto();
				pdto.setEmail(rs.getString("email"));
				pdto.setNmname(rs.getString("nmname"));
				pdto.setPhoneNumber(rs.getString("phoneNumber")); 
				pdto.setAddress(rs.getString("address")); 
				pdto.setGubun(rs.getString("gubun")); 
				pdto.setGubunnum(rs.getString("gubunnum"));

				memlist.add(pdto);
			}
			return memlist;			
		}
		
	}	
//         추가
		public void addmem (PhoneBookPDto pdto) throws Exception {
			Connection conn = open();
			if (pdto.getGubun().equals("가족")) {
				pdto.setGubunnum("10");
			} else if (pdto.getGubun().equals("친구")) {
				pdto.setGubunnum("20");
			} else if (pdto.getGubun().equals("기타")) {
				pdto.setGubunnum("30");
			}
			System.out.println(pdto + "DAO");
			String sql = "insert into PHONEMEMBER values (?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);		
			try(conn; pstmt) {
					pstmt.setString(1, pdto.getEmail());
					pstmt.setString(2, pdto.getnmname());
					pstmt.setString(3, pdto.getPhoneNumber());
					pstmt.setString(4, pdto.getAddress());
					pstmt.setString(5, pdto.getGubunnum());
					pstmt.executeUpdate();
			}			
		
		}

//						--------삭제처리---------
		public void delmem (String phoneNumber) throws SQLException {
			Connection conn = open();
			
			String sql = "delete from PHONEMEMBER where phoneNumber=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try(conn; pstmt) {
				pstmt.setString(1, phoneNumber);
				
				if(pstmt.executeUpdate() ==0 ) {
					throw new SQLException("에러");
				}
			}
		}
		
//		----------------수정--------------------
		public void modmem (PhoneBookPDto pdto) throws Exception {
			Connection conn = open();
			
			StringBuilder sql = new StringBuilder();
			sql.append("update PHONEMEMBER	 		");
			sql.append("set 	email = ?			");
			sql.append("    	,nmname = ?			");
			sql.append("    	,address = ?		");
			sql.append("    	,gubunnum = ?		");
			sql.append("where    phoneNumber = ?	");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			try(conn; pstmt) {
				pstmt.setString(1, pdto.getEmail());
				pstmt.setString(2, pdto.getnmname());
				pstmt.setString(3, pdto.getAddress());
				pstmt.setString(4, pdto.getGubunnum());
				pstmt.setString(5, pdto.getPhoneNumber());
				pstmt.executeUpdate();
			}
		}
	}
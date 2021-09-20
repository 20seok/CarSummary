package com.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.domain.Member2VO;

public class Member2DAO {
	// DB접속정보
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String user = "myuser";
	private final String pwd = "1111";

	// DB접속 후 커넥션 객체 가져오는 메소드
	private Connection getConnection() throws ClassNotFoundException, SQLException {

		Connection con = null;

		// 1단계. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2단계. DB 연결
		con = DriverManager.getConnection(url, user, pwd);

		return con;
	} // getConnection

	private void close(Connection con, PreparedStatement pstmt) {
		close(con, pstmt, null);
	}

	private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // close
	
	public void member2Insert(Member2VO member2VO) {
		
		// JDBC
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql 문장객체 타입
		
		try {
			con = getConnection();
			
			String sql = "";
			sql += "INSERT INTO member2 (id, pwd, name, reg_date) ";
			sql += "VALUES (?, ?, ?, ?) ";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, member2VO.getId());
			pstmt.setString(2, member2VO.getPwd());
			pstmt.setString(3, member2VO.getName());
			pstmt.setTimestamp(4, member2VO.getRegDate());
			
			pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
	} // insert;
	
	public int getCountById(String id) {
		int count = 0;
		
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql 문장객체 타입
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT COUNT(*) AS cnt FROM member2 WHERE id = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return count;
	} // getCountById
	
	public boolean member2Login(String id, String pwd) {
		String getPwd = null;
		boolean flag = false;
		
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql 문장객체 타입
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT pwd FROM member2 where id = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				getPwd = rs.getString("pwd");
				if(getPwd.equals(pwd)) {
					flag = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return flag;
	} // member2Login
	
	
	
	
	
	
	public static void main(String[] args) {
		
	} // main
}

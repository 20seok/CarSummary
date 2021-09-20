package com.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.domain.CarVO;

public class CarDAO {

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

	public void insertCar(CarVO carVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			String sql = "";
			sql += "INSERT INTO car (brand, util, year, name, cc, oil, price, img) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, carVO.getBrand());
			pstmt.setString(2, carVO.getUtil());
			pstmt.setInt(3, carVO.getYear());
			pstmt.setString(4, carVO.getName());
			pstmt.setInt(5, carVO.getCc());
			pstmt.setString(6, carVO.getOil());
			pstmt.setString(7, carVO.getPrice());
			pstmt.setString(8, carVO.getCarImg());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
	} // insertCar

	public List<CarVO> getCar() {

		List<CarVO> list = new ArrayList<>();

		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql 문장객체 타입
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "SELECT * FROM car ORDER BY brand ";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CarVO carVO = new CarVO();
				carVO.setBrand(rs.getString("brand"));
				carVO.setUtil(rs.getString("util"));
				carVO.setYear(rs.getInt("year"));
				carVO.setName(rs.getString("name"));
				carVO.setCc(rs.getInt("cc"));
				carVO.setOil(rs.getString("oil"));
				carVO.setPrice(rs.getString("price"));
				carVO.setCarImg(rs.getString("img"));

				list.add(carVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	}

	public void updateCar(CarVO carVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			String sql = "";
			sql += "UPDATE car SET brand = ?, util = ?, year = ?, cc = ?, oil = ?, price = ?, img = ? ";
			sql += "WHERE name = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, carVO.getBrand());
			pstmt.setString(2, carVO.getUtil());
			pstmt.setInt(3, carVO.getYear());
			pstmt.setInt(4, carVO.getCc());
			pstmt.setString(5, carVO.getOil());
			pstmt.setString(6, carVO.getPrice());
			pstmt.setString(7, carVO.getCarImg());
			pstmt.setString(8, carVO.getName());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
	} // updateCar

	public void removeCar(String name, int year) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			String sql = "DELETE FROM car WHERE name = ? and year = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, year);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
	} // removeCar

	public List<CarVO> search(String category, String word) {
		List<CarVO> list = new ArrayList<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "";
			sql += "SELECT * FROM car ";
			sql += "WHERE " + category + " LIKE ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + word + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CarVO carVO = new CarVO();
				carVO.setBrand(rs.getString("brand"));
				carVO.setUtil(rs.getString("util"));
				carVO.setYear(rs.getInt("year"));
				carVO.setName(rs.getString("name"));
				carVO.setCc(rs.getInt("cc"));
				carVO.setOil(rs.getString("oil"));
				carVO.setPrice(rs.getString("price"));
				carVO.setCarImg(rs.getString("img"));

				list.add(carVO);
			} // while

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	}

	public int getCountByName(String name) {
		int count = 0;

		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql 문장객체 타입
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "SELECT COUNT(*) AS cnt FROM car WHERE name = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return count;
	} // getCountByNames

	public int deleteAll() {
		int count = 0;

		// JDBC
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql 문장객체 타입

		try {
			con = getConnection();
			// sql문 준비
			String sql = "DELETE FROM car";
			// 3단계. pstmt 문장객체 생성
			pstmt = con.prepareStatement(sql);
			// 4단계. sql 문장 실행
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return count;
	} // deleteAll

	public int getCountAll() {
		int count = 0;

		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql 문장객체 타입
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "SELECT COUNT(*) AS cnt FROM car";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1); // 열(column)로 가져오기 // rs.getInt("cnt")로 해도됨
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return count;
	} // getCountAll

	public CarVO getCarByName(String name) {
		CarVO carVO = null;

		// JDBC
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql 문장객체 타입
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "SELECT * FROM car WHERE name = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				carVO = new CarVO();
				carVO.setBrand(rs.getString("brand"));
				carVO.setUtil(rs.getString("util"));
				carVO.setYear(rs.getInt("year"));
				carVO.setName(rs.getString("name"));
				carVO.setCc(rs.getInt("cc"));
				carVO.setOil(rs.getString("oil"));
				carVO.setPrice(rs.getString("price"));
				carVO.setCarImg(rs.getString("img"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}

		return carVO;
	}

	public static void main(String[] args) {

	} // main

}

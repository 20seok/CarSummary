package com.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.domain.CarVO;
import com.example.repository.CarDAO;

public class CarSummaryTests {

	// 픽스쳐
	private CarDAO carDAO;
	private CarVO carVO1, carVO2;

	@Before
	public void setUp() {
		System.out.println("@Before");
		
		// sample data
		carDAO = new CarDAO();

		carVO1 = new CarVO("현대", "세단", 2021, "에쿠스", 3600, "가솔린", "6000만원", "images/eq");
		carVO2 = new CarVO("기아", "SUV", 2021, "스포티지", 1600, "가솔린", "2800만원", "images/spotage");
	}

	@Test
	public void testConnection() {
		// DB 접속정보
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "myuser";
		String pwd = "1111";

		try {
			// 1단계. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2단계. DB 연결
			Connection con = DriverManager.getConnection(url, user, pwd);

			assertNotNull(con); // con 객체가 있는지 확인

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // testConnection

	@Test
	public void testDeleteAll() {
		carDAO.deleteAll();

		int count = carDAO.getCountAll();

		assertEquals(0, count);
	} // testDeleteAll
	
	@Test
	public void testRemoveCar() { // name과 year를 기준으로 차를 삭제시킴
		carDAO.deleteAll();
		
		carDAO.insertCar(carVO1);
		
		int count = carDAO.getCountAll();
		
		assertEquals(1, count);
		
		carDAO.removeCar(carVO1.getName(), carVO1.getYear());
		
		int count2 = carDAO.getCountAll();
		
		assertEquals(0, count2);
	} // testRemoveCar

	@Test
	public void testInsertCar() {
		carDAO.deleteAll();

		carDAO.insertCar(carVO1);
		carDAO.insertCar(carVO2);

		assertEquals(2, carDAO.getCountAll());
	} // testInsertCar

	@Test
	public void testUpdateCar() {
		carDAO.deleteAll();

		carDAO.insertCar(carVO1);

		CarVO updateCar = new CarVO("현대", "세단", 2021, "에쿠스", 1600, "가솔린", "5000만원", "images/eq.jpg");

		carDAO.updateCar(updateCar);

		CarVO dbCar = carDAO.getCarByName(updateCar.getName());

		assertEquals(updateCar.getName(), dbCar.getName());
	} // testUpdateCar
	
	@Test
	public void testGetCar() {
		carDAO.deleteAll();
		
		List<CarVO> list = carDAO.getCar();
		assertEquals(0, list.size());
		
		CarVO carVO = new CarVO("기아", "SUV", 2021, "스포티지", 1600, "가솔린", "2800만원", "images/spotage");
		
		carDAO.insertCar(carVO);
		
		list = carDAO.getCar();
		assertEquals(1, list.size());
	} // testGetCar
	
	@After
	public void tearDown() {
		System.out.println("@After");
	}

}

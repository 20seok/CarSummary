package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarVO {
	private String brand;
	private String util; // SUV, 세단, 스포츠카
	private int year;
	private String name;
	private int cc;
	private String oil; // 가솔린, 디젤
	private String price;
	private String carImg; // DB 저장용 이미지 경로
}

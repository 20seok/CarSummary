package com.example.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member2VO {
	private String id;
	private String pwd;
	private String name;
	private Timestamp regDate;
}

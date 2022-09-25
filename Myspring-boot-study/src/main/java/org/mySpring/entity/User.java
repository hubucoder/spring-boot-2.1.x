package org.mySpring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	private Integer id;
	private String name;
	private Integer gender;
	private Integer age;
	private String addr;
}

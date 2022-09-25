package org.mySpring.controller;

import org.mySpring.entity.User;
import org.mySpring.mapper.UserMappper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserMappper mappper;

	@GetMapping("/findOne")
	@ResponseBody
	public User findOne() {
		User u = mappper.findOne(1);
		return u;
	}

	@GetMapping("find")
	@ResponseBody
	public User find() {
		return mappper.find(1);
	}
}

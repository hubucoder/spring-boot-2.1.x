package org.mySpring.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mySpring.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMappper {
	@Select("select * from user where id = ${id}")
	User findOne(int id);

	User find(int id);
}

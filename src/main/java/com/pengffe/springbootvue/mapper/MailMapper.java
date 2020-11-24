package com.pengffe.springbootvue.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MailMapper {

    @Insert("insert into user(code) value code = #{code}")
    boolean createVerifyCode(String code);

    @Select("select code from user where user = #{username}")
    String emailVerify(String username);

}

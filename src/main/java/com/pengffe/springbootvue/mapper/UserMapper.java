package com.pengffe.springbootvue.mapper;

import com.pengffe.springbootvue.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select count(*) from user where username = #{username}")
    Integer countByUsername(String username);

    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    @Insert("insert into User(id, username, password, lastName, firstName, enrollTime, school) value\n" +
            "                      (null, #{username}, #{password}, #{lastName}, null, null, null)")
    void addUser(User user);

    @Select("select password from user where username = #{username}")
    String selectPasswordByUsername(String username);

    @Select("select * from user where id = #{id}}")
    User selectById(Integer id);

    @Select("select username, lastName, firstName from user where school = #{type} and username <> #{username}" +
            "and id not in (select uid from friends where id = (select id from user where username = #{username}))")
    List<User> selectUserByType(String type, String username);

    @Select("select username, lastName, firstName from user \n" +
            "where id in\n" +
            "\t(select uid from friends f\n" +
            "\t\twhere f.id = (SELECT id from user where username = #{username}))")
    List<User> selectFriendsByUsername(String username);

    @Insert("insert into friends(id, uid) value(" +
            "(select id from user where username = #{username}), " +
            "(select id from user where username = #{friendUsername})" +
            ")")
    boolean addFriendToUser(String username, String friendUsername);

    @Delete("delete from friends " +
            "where id = (select id from user where username = #{username})" +
            "and uid = (select id from user where username = #{friendUsername})")
    boolean removeFriendToUser(String username, String friendUsername);

}

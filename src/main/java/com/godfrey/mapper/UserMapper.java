package com.godfrey.mapper;

import com.godfrey.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description : 登录验证查询Mapper
 *
 * @author godfrey
 * @since 2020-05-26
 */
@Mapper
@Repository
public interface UserMapper {
    User selectPasswordByName(@Param("userName") String userName, @Param("password") String password);
    User selectUserInfoById(@Param("id") Integer id);
    List<User> empInfoFromSameDep(@Param("empId") Integer empId);
    int updateUserInfo(@Param("id") Integer id, @Param("user_name") String user_name,@Param("password") String password);
    List<User> selectEmpInfoList();
    int deleteEmp(@Param("id") Integer id);
    int addUser(@Param("id") Integer id, @Param("user_name") String user_name,
                @Param("password") String password, @Param("empId") Integer empId);
}

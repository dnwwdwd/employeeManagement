package com.godfrey.service;

import com.godfrey.pojo.User;

import java.util.List;

/**
 * description : User业务层
 *
 * @author godfrey
 * @since 2020-05-26
 */
public interface UserService {
    User selectPasswordByName(String userName, String password);
    User userInfo(int id);
    List<User> empInfoFromSameDep(int empId);
    int updateUserInfo(User user);
    List<User> selectEmpInfoList();
    int deleteEmp(int id);
    int addUser(User user);
}

package com.godfrey.service.impl;

import com.godfrey.mapper.UserMapper;
import com.godfrey.pojo.User;
import com.godfrey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description : UserServiceImpl
 *
 * @author godfrey
 * @since 2020-05-26
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectPasswordByName(String userName, String password) {
        return userMapper.selectPasswordByName(userName, password);
    }

    @Override
    public User userInfo(int id) {
        return userMapper.selectUserInfoById(id);
    }

    @Override
    public List<User> empInfoFromSameDep(int empId) {
        return userMapper.empInfoFromSameDep(empId);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUserInfo(user.getId(), user.getUserName(), user.getPassword());
    }

    @Override
    public List<User> selectEmpInfoList() {
        return userMapper.selectEmpInfoList();
    }

    @Override
    public int deleteEmp(int id) {
        return userMapper.deleteEmp(id);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user.getId(), user.getUserName(), user.getPassword(), user.getEmpId());
    }
}

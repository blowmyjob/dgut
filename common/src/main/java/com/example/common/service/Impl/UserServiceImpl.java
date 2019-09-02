package com.example.common.service.Impl;

import com.example.common.entity.Resume;
import com.example.common.entity.SysRole;
import com.example.common.entity.User;
import com.example.common.mapper.PermDao;
import com.example.common.mapper.UserDao;
import com.example.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PermDao permDao;

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public void delUser(Integer userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public void updateUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> getManagers() {
        return userDao.getManagers();
    }

    @Override
    public List<SysRole> getRoles(String userName) {
        return permDao.getRoles(userName);
    }

    @Override
    public List<Resume> getResumes(String userName) {
        return userDao.getResumes(userName);
    }

    @Override
    public void deleteResume(Integer userId) {
        userDao.deleteResume(userId);
    }

    @Override
    public void addResume(Resume resume) {
        userDao.addResume(resume);
    }

    @Override
    public void updatePawd(String newPawd, String userName) {
        userDao.updatePasswd(newPawd,userName);
    }

    @Override
    public String findPasswd(String userName) {
        return userDao.findPasswd(userName);
    }

    @Override
    public void checkUser(Integer id) {
        userDao.checkUser(id);
    }
}

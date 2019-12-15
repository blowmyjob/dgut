package com.example.common.mapper;

import com.example.common.entity.Resume;
import com.example.common.entity.SysPermission;
import com.example.common.entity.SysRole;
import com.example.common.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    /**
     * 列出用户
     * @return
     */
    public List<User> getUsers();

    public User getUser(Integer id);

    public User getUserByName(String userName);

    /**
     * 找出密码
     * @param userName
     * @return
     */
    public String findPasswd(String userName);

    /**
     * 删除用户
     * @param userId
     */
    public void deleteUser(Integer userId);

    /**
     * 修改用户信息
     * @param user
     */
    public void updateUser(User user);

    /**
     * 修改密码
     * @param password
     * @param username
     */
    public void updatePasswd(String password,String username);

    /**
     * 注册用户
     * @param user
     */
    public void addUser(User user);

    /**
     * 删除简历
     * @param id
     */
    public void deleteResume(Integer id);

    /**
     * 列出简历
     * @param userName
     * @return
     */
    public List<Resume> getResumes(String userName);

    /**
     * 新建简历
     * @param resume
     */
    public void addResume(Resume resume);

    /**
     * 启用用户
     * @param id
     */
    public void checkUser(Integer id);

    /**
     * 停用用户
     * @param id
     */
    public void stopUser(Integer id);

    /**************管理员模块****************/
    /**
     * 列出所有的管理员
     * @return
     */
    public List<User> getManagers();

}

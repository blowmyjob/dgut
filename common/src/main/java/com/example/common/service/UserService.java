package com.example.common.service;

import com.example.common.entity.Resume;
import com.example.common.entity.SysRole;
import com.example.common.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 删除用户
     * @param userId
     */
    public void delUser(Integer userId);

    /**
     * 通过用户名查找用户
     * @param userName
     * @return
     */
    public User getUser(String userName);

    /**
     * 通过id查找用户
     * @param userId
     * @return
     */
    public User getUser(Integer userId);
    /**
     * 修改用户
     * @param user
     */
    public void updateUser(User user);

    /**
     * 添加/注册用户
     * @param user
     */
    public void addUser(User user);


    /**
     * 修改密码
     * @param newPawd
     * @param userName
     */
    public void updatePawd(String newPawd,String userName);

    /**
     * 列出所有的用户
     * @return
     */
    List<User> getUsers();

    /**
     * 查询简历
     * @param userName
     * @return
     */
    List<Resume> getResumes(String userName);

    /**
     * 删除简历
     * @param userId
     */
    void deleteResume(Integer userId);

    /**
     * 新建简历
     * @param resume
     */
    void addResume(Resume resume);
    /**
     * 找出密码
     * @param userName
     * @return
     */
    String findPasswd(String userName);

    /**
     * 用户审核
     * @param id
     */
    void checkUser(Integer id);

    /**
     * 找出用户的角色和权限
     * @param userName
     * @return
     */
    List<SysRole>getRoles(String userName);

    /**
     * 查找出所有的管理员
     * @return
     */
    List<User>getManagers();


    /**
     * 停用用户
     * @param userId
     */
    void stopUser(Integer userId);

    /**
     * 查找某一类型的用户
     */
    List<User>selectUserByType(Map<String,String>map);

    /**
     * 批量删除用户
     * @param ids
     */
    void delUsers(List<Integer>ids);
}

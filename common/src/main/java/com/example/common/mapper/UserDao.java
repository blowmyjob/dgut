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
    @Select("select * from User")
    @Results({@Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "identify",column = "identify"),
            @Result(property = "available",column = "available"),
            @Result(property = "entertime",column = "entertime")})
    public List<User> getUsers();

    @Select("select * from User where id = #{id}")
    public User getUser(Integer id);

    @Select("select * from User where userName = #{userName}")
    public User getUserByName(String userName);
    /**
     * 找出密码
     * @param userName
     * @return
     */
    @Select("select password from User where username = #{userName}")
    public String findPasswd(String userName);

    /**
     * 删除用户
     * @param userId
     */
    @Delete("delete from User where id = #{id}")
    public void deleteUser(Integer userId);

    /**
     * 修改用户信息
     * @param user
     */
    @Update("update User set username = #{username}, sex = #{sex} where id= #{id}")
    public void updateUser(User user);

    /**
     * 修改密码
     * @param password
     * @param username
     */
    @Update("update User set password = #{password} where username = #{username}")
    public void updatePasswd(String password,String username);

    /**
     * 注册用户
     * @param user
     */
    @Insert("insert into User (username,password,sex,identify,available) values(#{username},#{password},#{sex},#{identify},'false')")
    public void addUser(User user);

    /**
     * 删除简历
     * @param id
     */
    @Delete("delete from  Resume where id = #{id}")
    public void deleteResume(Integer id);

    /**
     * 列出简历
     * @param userName
     * @return
     */
    @Select("select * from Resume where userName = #{userName}")
    public List<Resume> getResumes(String userName);

    /**
     * 新建简历
     * @param resume
     */
    @Insert("insert into Resume (fileName,address,userName) values (#{fileName},#{address},#{userName})")
    public void addResume(Resume resume);

    /**
     * 启用用户
     * @param id
     */
    @Update("update User set available = 'true' where id = #{id}")
    public void checkUser(Integer id);

    /**
     * 停用用户
     * @param id
     */
    @Update("update User set available = 'false' where id = #{id}")
    public void stopUser(Integer id);

    /**************管理员模块****************/
    /**
     * 列出所有的管理员
     * @return
     */
    @Select("select * from User where identify = 'ADMIN' ")
    public List<User> getManagers();

}

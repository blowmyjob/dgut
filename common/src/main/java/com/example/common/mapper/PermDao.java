package com.example.common.mapper;

import com.example.common.entity.SysPermission;
import com.example.common.entity.SysRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermDao {

    @Select("select * from SysRole where username = #{userName}")
    @Results({@Result(property = "id",column = "id"),
              @Result(property = "role",column = "role"),
              @Result(property = "description",column = "description"),
              @Result(property = "username",column = "username"),
              @Result(property = "syspermissions",javaType = List.class,column ="role",
                    many = @Many(select = "com.example.common.mapper.PermDao.getPermsByRole"))})
    public List<SysRole> getRoles(String userName);

    @Select("select * from SysPermission where name = #{role}")
    public List<SysPermission> getPermsByRole(String role);

    @Select("select * from SysPermission")
    public List<SysPermission> getPerm();

    @Insert("insert into SysPermission (name,url,permission) values (#{name},#{url},#{permission})")
    public void addPerm(SysPermission sysPermission);

    @Update("update SysPermission set name = #{name},url = #{url},permission=#{permission} where id = #{id}")
    public void updatePerm(SysPermission sysPermission,@Param(value = "id") Integer id);

    @Delete("delete from SysPermission where id = #{id}")
    public void delPerm(Integer id);

    @Select("select * from SysRole")
    public List<SysRole> getRole();

    @Insert("insert into SysRole (role,description,username) values (#{role},#{description},#{username})")
    public void addRole(SysRole role);

    @Update("update SysRole set role = #{role},description=#{description},username=#{username} where id = #{id}")
    public void updateRole(SysRole role,@Param(value = "id") Integer id);

    @Delete("delete from SysRole where id = #{id}")
    public void deleteRole(Integer id);
}

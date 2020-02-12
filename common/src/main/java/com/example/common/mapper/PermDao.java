package com.example.common.mapper;

import com.example.common.entity.SysPermission;
import com.example.common.entity.SysRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermDao {
    public List<SysRole> getRoles(String userName);

    public List<SysPermission> getPermsByRole(String role);

    public List<SysPermission> getPerm();

    public void addPerm(SysPermission sysPermission);

    public void updatePerm(@Param(value = "sysPermission") SysPermission sysPermission,@Param(value = "id") Integer id);

    public void delPerm(Integer id);

    public List<SysRole> getRole();

    public Integer addRole(SysRole role);

    public void updateRole(@Param(value = "role") SysRole role,@Param(value = "id") Integer id);

    public void deleteRole(Integer id);

    public SysRole getRoleById(Integer id);

    public void addRolePerm(@Param("lists")List<Integer>lists,@Param("id") Integer id);

    public void delRolePerm(Integer id);

    public void delRolePermByPerm(Integer id);

    public void delRoles(List<Integer>list);

    public void delPerms(List<Integer>list);

    public List<SysPermission> getPermByUserid(Integer id);
}

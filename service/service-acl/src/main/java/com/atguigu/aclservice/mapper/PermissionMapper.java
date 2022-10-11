package com.atguigu.aclservice.mapper;

import com.atguigu.aclservice.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface PermissionMapper extends BaseMapper<Permission> {


    @Select("select\n" +
            "            p.permission_value\n" +
            "        from acl_user_role ur\n" +
            "                 inner join acl_role_permission rp on rp.role_id = ur.role_id\n" +
            "                 inner join acl_permission p on p.id = rp.permission_id\n" +
            "        where ur.user_id = #{userId}\n" +
            "          and p.type = 2\n" +
            "          and ur.is_deleted = 0\n" +
            "          and rp.is_deleted = 0\n" +
            "          and p.is_deleted = 0")
    List<String> selectPermissionValueByUserId(String id);

    @Select("select\n" +
            "            permission_value\n" +
            "        from acl_permission\n" +
            "        where type = 2\n" +
            "          and is_deleted = 0")
    List<String> selectAllPermissionValue();

    @Select("select\n" +
            "        p.id,p.pid,p.name,p.type,p.permission_value,path,p.component,p.icon,p.status,p.is_deleted,p.gmt_create,p.gmt_modified" +
            "        from acl_user_role ur\n" +
            "        inner join acl_role_permission rp on rp.role_id = ur.role_id\n" +
            "        inner join acl_permission p on p.id = rp.permission_id\n" +
            "        where ur.user_id = #{userId}\n" +
            "        and ur.is_deleted = 0\n" +
            "        and rp.is_deleted = 0\n" +
            "        and p.is_deleted = 0")
    @Results(id = "aaa", value = {
            @Result(property = "id", id = true, column = "id"),
            @Result(property = "pid", column = "pid"),
            @Result(property = "name", column = "name"),
            @Result(property = "type", column = "type"),
            @Result(property = "permissionValue", column = "permission_value"),
            @Result(property = "path", column = "path"),
            @Result(property = "component", column = "component"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "component", column = "component"),
            @Result(property = "status", column = "status"),
            @Result(property = "isDeleted", column = "is_deleted"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    List<Permission> selectPermissionByUserId(String userId);
}

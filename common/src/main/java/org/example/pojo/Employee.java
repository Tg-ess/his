package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyx
 * @since 2024-12-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名  加上   用于登录
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 密码
     */
    private String password;

    /**
     * 所在科室ID
     */
    private Integer deptmentId;

    /**
     * 挂号级别ID
     */
    private Integer registLevelId;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 班排id
     */
    private Integer schedulingId;

    /**
     * 生效标记
     */
    private Integer delmark;

    /*
    关联部门对象
     */
    @TableField(exist = false)
    private Department department;

    /*
    关联的挂号级别对象
     */
    @TableField(exist = false)
    private RegistLevel registLevel;

    /*
    关联的角色对象
     */
    @TableField(exist = false)
    private Role role;

}

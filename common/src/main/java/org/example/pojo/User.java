package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author cyx
 * @since 2024-12-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String email;

    private String phone;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 密码，md5加密加盐
     */
    private String password;

    /**
     * 已注册 已激活 禁用
     */
    private Integer state;

    private Integer age;

    private LocalDateTime createtime;

    private String headImg;

    private Long logininfoId;

}

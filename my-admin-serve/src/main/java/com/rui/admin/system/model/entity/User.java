package com.rui.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rui.admin.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * 系统用户
 *
 * @author Rui
 * @since 2022-03-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    /**
    * 用户名
    */
    private String username;


    /**
    * 密码
    */
    private String password;


    /**
    * 姓名
    */
    private String name;


    /**
    * 性别 0-男 1-女
    */
    private Integer gender;


    /**
    * 出身日期
    */
    private LocalDateTime birthday;


    /**
    * 电话
    */
    private String phone;


    /**
    * 住址
    */
    private String address;


    /**
    * 邮箱
    */
    private String email;

    /**
     * 是否允许
     */
    private Boolean enable;

}

package com.rui.admin.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rui.admin.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 系统日志
 *
 * @author Rui
 * @since 2022-04-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_log")
@Accessors(chain = true)
public class Log extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
    * 请求地址
    */
    private String requestUrl;


    /**
    * 业务名称
    */
    private String businessName;


    /**
    * 请求方式
    */
    private String httpMethod;


    /**
    * 类名
    */
    private String className;


    /**
    * 请求方法
    */
    private String method;


    /**
    * 请求IP
    */
    private String ip;

}

package com.rui.admin.commons.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author rui
 * @since 2022/2/24 3:33 PM
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 状态码
     */
    private Integer code = 200;

    /**
     * 消息
     */
    private String msg = "";

    /**
     * 第几页
     */
    private Long page = 1L;

    /**
     * 每页条数
     */
    private Long limit = 20L;

    /**
     * 总记录数
     */
    private Long count = 0L;

    /**
     * 结果集
     */
    private List<T> data;

    public PageResult() {
    }

    /**
     * 将mybatis-plus的page转成自定义的PageResult
     */
    public PageResult(Page<T> page) {
        this.setData(page.getRecords());
        this.setCount(page.getTotal());
        this.setPage(page.getCurrent());
        this.setLimit(page.getSize());
    }

    /**
     * 将list转为分页方式（无分页）
     */
    public PageResult(List<T> t) {
        this.setData(t);
    }

    /**
     * 将mybatis-plus的page转成自定义的PageResult
     * 可单独设置rows
     */
    public PageResult(Page<T> page, List<T> t) {
        this.setData(t);
        this.setCount(page.getTotal());
        this.setPage(page.getCurrent());
        this.setLimit(page.getSize());
    }

}


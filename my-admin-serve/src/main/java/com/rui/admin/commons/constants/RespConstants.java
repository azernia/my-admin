package com.rui.admin.commons.constants;

public interface RespConstants {

    /**
     * 成功代码
     */
    int SUCCESS_CODE = 200;

    /**
     * 失败代码
     */
    int ERROR_CODE = 500;

    /**
     * 需要登录
     */
    int NEED_LOGIN = 401;

    /**
     * 账户锁定
     */
    int LOCKED = 423;

    /**
     * 添加成功
     */
    String ADD_SUCCESS = "添加成功";

    /**
     * 删除成功
     */
    String DELETE_SUCCESS = "删除成功";

    /**
     * 修改成功
     */
    String UPDATE_SUCCESS = "修改成功";

    /**
     * 添加失败
     */
    String ADD_FAIL = "添加失败";

    /**
     * 更新失败
     */
    String UPDATE_FAIL = "修改失败";

    /**
     * 删除失败
     */
    String DELETE_FAIL = "删除失败";

    /**
     * 此数据不存在
     */
    String NO_EXIST = "此数据不存在";

}

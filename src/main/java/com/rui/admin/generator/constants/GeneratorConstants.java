package com.rui.admin.generator.constants;

import com.rui.admin.generator.utils.YmlUtils;

public class GeneratorConstants {

    /**
     * URL
     */
    private final String URL = YmlUtils.getApplicationValue("spring.datasource.druid.url");

    /**
     * 用户名
     */
    private final String USERNAME = YmlUtils.getApplicationValue("spring.datasource.druid.username");

    /**
     * 密码
     */
    private final String PASSWORD = YmlUtils.getApplicationValue("spring.datasource.druid.password");

    /**
     * 驱动名
     */
    private final String DRIVER_CLASS_NAME = YmlUtils.getApplicationValue("spring.datasource.driver-class-name");

    /**
     * 模块名
     */
    private final String MODULE_NAME = YmlUtils.getSettingsValue("setting.moduleName");

    /**
     * 作者
     */
    private final String AUTHOR = YmlUtils.getSettingsValue("setting.author");

    /**
     * 去除的表前缀
     */
    private final String TABLE_PREFIX = YmlUtils.getSettingsValue("setting.tablePrefix");

    private final String TABLE_NAME = YmlUtils.getSettingsValue("setting.tableName");

    /**
     * 包路径
     */
    private final String PACKAGE_PATH = YmlUtils.getSettingsValue("setting.packagePath") + '.' + MODULE_NAME;

    /**
     * 后端代码生成路径
     */
    private final String SERVE_OUT_PATH = YmlUtils.getSettingsValue("setting.serveOutPath");

    private final String SERVE_MODEL_OUT_PATH = SERVE_OUT_PATH + YmlUtils.getSettingsValue("setting.serveModelOutPath");

    /**
     * 前端代码生成路径
     */
    private final String FONT_END_OUT_PATH = YmlUtils.getSettingsValue("setting.fontEndOutPath");

    /**
     * 模板路径
     */
    private static final String TEMPLATE_ROOT = "template";

    private final String CONTROLLER = TEMPLATE_ROOT + "/" + YmlUtils.getSettingsValue("setting.template.controller");
    private final String MAPPER = TEMPLATE_ROOT + "/" + YmlUtils.getSettingsValue("setting.template.mapper");
    private final String XML = TEMPLATE_ROOT + "/" + YmlUtils.getSettingsValue("setting.template.xml");
    private final String SERVICE = TEMPLATE_ROOT + "/" + YmlUtils.getSettingsValue("setting.template.service");
    private final String SERVICE_IMPL = TEMPLATE_ROOT + "/" + YmlUtils.getSettingsValue("setting.template.serviceImpl");
    private final String ENTITY = TEMPLATE_ROOT + "/" + YmlUtils.getSettingsValue("setting.template.model.entity");
    private final String REQUEST = TEMPLATE_ROOT + "/" + YmlUtils.getSettingsValue("setting.template.model.request");
    private final String RESPONSE = TEMPLATE_ROOT + "/" + YmlUtils.getSettingsValue("setting.template.model.response");
    private final String VUE = TEMPLATE_ROOT + "/" + YmlUtils.getSettingsValue("setting.template.vue");

    public String getURL() {
        return URL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getDRIVER_CLASS_NAME() {
        return DRIVER_CLASS_NAME;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public String[] getTABLE_PREFIX() {
        return TABLE_PREFIX.split(",");
    }

    public String getPACKAGE_PATH() {
        return PACKAGE_PATH;
    }

    public String getSERVE_OUT_PATH() {
        return SERVE_OUT_PATH;
    }

    public String getFONT_END_OUT_PATH() {
        return FONT_END_OUT_PATH;
    }

    public String getCONTROLLER() {
        return CONTROLLER;
    }

    public String getMAPPER() {
        return MAPPER;
    }

    public String getXML() {
        return XML;
    }

    public String getSERVICE() {
        return SERVICE;
    }

    public String getSERVICE_IMPL() {
        return SERVICE_IMPL;
    }

    public String getENTITY() {
        return ENTITY;
    }

    public String getREQUEST() {
        return REQUEST;
    }

    public String getRESPONSE() {
        return RESPONSE;
    }

    public String getVUE() {
        return VUE;
    }

    public String getSERVE_MODEL_OUT_PATH() {
        return SERVE_MODEL_OUT_PATH;
    }

    public String[] getTABLE_NAME() {
        return TABLE_NAME.split(",");
    }

    public static void main(String[] args) {
        String str = "setting, a";
        String[] split = str.trim().split(",");
        for (String s : split) {
            System.out.println(s);
        }
    }
}

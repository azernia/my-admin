package com.rui.admin.generator.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;
import java.util.Objects;

/**
 * @author rui
 * @date 2022/2/16 5:50 PM
 */
public class YmlUtils {

    /**
     * Application yml path
     */
    private static final String APPLICATION_YML_PATH = "application-dev.yml";

    /**
     * 代码生成器 yml
     */
    private static final String SETTINGS_YML_PATH = "com/rui/admin/generator/settings.yml";

    private YmlUtils() {};

    /**
     * 读取 yml
     * @param path 路径
     */
    private static JSONObject loadYml(String path) {
        Yaml yaml = new Yaml();
        Map<String, Object> loadYml = yaml.load(YmlUtils.class.getClassLoader().getResourceAsStream(path));
        return JSONObject.parseObject(JSON.toJSONString(loadYml));
    }

    public static String getApplicationValue(String key) {
        return getValue(APPLICATION_YML_PATH, key);
    }

    public static String getSettingsValue(String key) {
        return getValue(SETTINGS_YML_PATH, key);
    }

    private static String getValue(String path, String key) {
        JSONObject jsonObject = loadYml(path);
        String[] split = key.split("\\.");
        String value = "";
        for (int i = 0; i < split.length; i++) {
            if(i == split.length - 1) {
                value = jsonObject.getString(split[i]);
            } else {
                jsonObject = jsonObject.getJSONObject(split[i]);
            }
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(Objects.requireNonNull(YmlUtils.class.getClassLoader().getResource("/")).getPath());
    }
}

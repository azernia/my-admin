package com.rui.admin.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.rui.admin.commons.entity.BaseEntity;
import com.rui.admin.generator.constants.GeneratorConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rui
 * @since 2022/2/16 8:54 AM
 */
public class Execution {

    private final GeneratorConstants generatorConstants = new GeneratorConstants();

    public void generatorExecute() {
        // 创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 设置使用的模板引擎
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(generatorConstants.getCONTROLLER());
        templateConfig.setEntity(generatorConstants.getENTITY());
        templateConfig.setMapper(generatorConstants.getMAPPER());
        templateConfig.setXml(generatorConstants.getXML());
        templateConfig.setService(generatorConstants.getSERVICE());
        templateConfig.setServiceImpl(generatorConstants.getSERVICE_IMPL());
        autoGenerator.setTemplate(templateConfig);

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                String packagePath = generatorConstants.getPACKAGE_PATH();
                Map<String, Object> map = new HashMap<>();
                map.put("packagePath", packagePath);
                this.setMap(map);
            }
        };

        List<FileOutConfig> fileOutConfigs = new ArrayList<>();
        //  自定义前端文件生成
        // fileOutConfigs.add(new FileOutConfig(generatorConstants.getVUE()) {
        //     @Override
        //     public String outputFile(TableInfo tableInfo) {
        //         return generatorConstants.getFONT_END_OUT_PATH() + "/" + tableInfo.getEntityName() + ".vue";
        //     }
        // });
        // 自定义 DTO
        fileOutConfigs.add(new FileOutConfig(generatorConstants.getREQUEST()) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorConstants.getSERVE_MODEL_OUT_PATH() + "/request/" + tableInfo.getEntityName() + "DTO.java";
            }
        });
        // 自定义 VO
        fileOutConfigs.add(new FileOutConfig(generatorConstants.getRESPONSE()) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorConstants.getSERVE_MODEL_OUT_PATH() + "/response/" + tableInfo.getEntityName() + "VO.java";
            }
        });
        injectionConfig.setFileOutConfigList(fileOutConfigs);
        autoGenerator.setCfg(injectionConfig);

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(generatorConstants.getSERVE_OUT_PATH())
                .setAuthor(generatorConstants.getAUTHOR())
                .setOpen(false) //是否打开资源管理器
                .setKotlin(false) // 是否生成 Kotlin 代码
                .setFileOverride(true) // 重新生成时是否覆盖
                .setServiceName("%sService")
                .setEnableCache(false) // 二级缓存
                .setIdType(IdType.AUTO)    // 主键生成策略
                .setDateType(DateType.TIME_PACK)    // 定义实体类中日期类型
                .setSwagger2(false);
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(generatorConstants.getURL())
                .setDriverName(generatorConstants.getDRIVER_CLASS_NAME())
                .setUsername(generatorConstants.getUSERNAME())
                .setPassword(generatorConstants.getPASSWORD())
                .setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(null)
                .setParent(generatorConstants.getPACKAGE_PATH())
                .setController("controller")
                .setEntity("model.entity")
                .setService("service")
                .setServiceImpl("service.impl")
                .setMapper("mapper")
                .setXml("mapper");
        autoGenerator.setPackageInfo(packageConfig);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude(generatorConstants.getTABLE_NAME())
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix(generatorConstants.getTABLE_PREFIX())    // 去除前缀
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setSuperEntityClass(BaseEntity.class);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }

    public static void main(String[] args) {
        new Execution().generatorExecute();
    }
}

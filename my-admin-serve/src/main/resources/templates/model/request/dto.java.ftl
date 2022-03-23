package ${cfg.packagePath}.model.request;

<#--<#list table.importPackages as pkg>-->
<#--import ${pkg};-->
<#--</#list>-->
import java.io.Serializable;
<#if entityLombokModel>
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
    <#if chainModel>
import lombok.experimental.Accessors;
    </#if>
</#if>
/**
 * ${table.comment!} DTO
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
@AllArgsConstructor
@NoArgsConstructor
    <#if chainModel>
@Accessors(chain = true)
    </#if>
</#if>
public class ${entity}DTO implements Serializable {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
        <#if field.propertyName != "version" && field.propertyName != "createTime" && field.propertyName != "updateTime" && field.propertyName != "deleted">
    /**
     * ${field.comment}
     */
        </#if>
    </#if>
    <#if field.propertyName != "version" && field.propertyName != "createTime" && field.propertyName != "updateTime" && field.propertyName != "deleted">
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->
}

package com.rui.admin.commons.serializer.decimal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.rui.admin.commons.annotation.decimal.BigDecimalFormat;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * BigDecimal 序列化
 * @author rui
 * @since 2022/3/16 16:05
 */
@JsonComponent
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> implements ContextualSerializer {

    // 默认格式化方案, 项目中添加了 BigDecimal 的格式化配置后,
    // 所有未添加 @BigDecimalFormat 注解的 BigDecimal 数据都会变成这个格式;
    private String format = "#.00";

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if(beanProperty !=null ){
            if(Objects.equals(beanProperty.getType().getRawClass(),BigDecimal.class)){
                BigDecimalFormat bigDecimalFormat = beanProperty.getAnnotation((BigDecimalFormat.class));
                if(bigDecimalFormat == null){
                    bigDecimalFormat = beanProperty.getContextAnnotation(BigDecimalFormat.class);
                }
                BigDecimalSerializer bigDecimalSerializer = new BigDecimalSerializer();
                if(bigDecimalFormat != null){
                    bigDecimalSerializer.format = bigDecimalFormat.value();
                }
                return bigDecimalSerializer;
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(),beanProperty);
        }
        return serializerProvider.findNullValueSerializer(beanProperty);
    }

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(new DecimalFormat(format).format(bigDecimal));
    }
}


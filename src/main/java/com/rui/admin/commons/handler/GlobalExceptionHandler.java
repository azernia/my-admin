package com.rui.admin.commons.handler;

import com.rui.admin.commons.constants.RespConstant;
import com.rui.admin.commons.entity.RespBean;
import com.rui.admin.commons.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author rui
 * @since 2022/2/10 9:35 AM
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public RespBean businessExceptionHandler(BusinessException e) {
        log.error("============>业务异常:", e);
        return RespBean.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public RespBean bindExceptionHandler(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (!Objects.isNull(fieldError)) {
            log.info("============>必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
            return RespBean.fail(RespConstant.ERROR_CODE, fieldError.getDefaultMessage());
        }
        return RespBean.fail(RespConstant.ERROR_CODE, "系统异常");
    }

    @ExceptionHandler(Exception.class)
    public RespBean exceptionHandler(Exception e) {
        log.error("============>系统异常:", e);
        return RespBean.fail(RespConstant.ERROR_CODE, "系统异常");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public RespBean accessDeniedExceptionHandler(AccessDeniedException accessDeniedException) {
        log.error("============>不允许访问:", accessDeniedException);
        return RespBean.fail(RespConstant.ERROR_CODE, accessDeniedException.getMessage());
    }

}

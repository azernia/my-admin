package com.rui.admin.system.service.impl;

import com.rui.admin.system.model.entity.Log;
import com.rui.admin.system.mapper.LogMapper;
import com.rui.admin.system.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* 系统日志 服务实现类
*
* @author Rui
* @since 2022-04-19
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}

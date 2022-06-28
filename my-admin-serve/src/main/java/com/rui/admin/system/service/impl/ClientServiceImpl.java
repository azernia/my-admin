package com.rui.admin.system.service.impl;

import com.rui.admin.system.model.entity.Client;
import com.rui.admin.system.mapper.ClientMapper;
import com.rui.admin.system.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* 系统客户端信息 服务实现类
*
* @author Rui
* @since 2022-06-28
*/
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

}

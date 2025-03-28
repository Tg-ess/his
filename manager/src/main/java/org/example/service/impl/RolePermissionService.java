package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.RolePermissionMapper;
import org.example.pojo.RolePermission;
import org.example.service.IRolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionService extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {
}

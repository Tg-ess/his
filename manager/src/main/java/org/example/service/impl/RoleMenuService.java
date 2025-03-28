package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.RoleMenuMapper;
import org.example.pojo.RoleMenu;
import org.example.service.IRoleMenuService;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
}

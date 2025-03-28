package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.MenuMapper;
import org.example.pojo.Menu;
import org.example.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyx
 * @since 2024-12-19
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> tree(Wrapper wrapper) {

        List<Menu> firstLevel = new ArrayList<>();

        Map<Long,Menu> mapping = new HashMap<>();

        List<Menu> allMenu = menuMapper.selectList(wrapper);

        //第一次遍历  找出所有一级菜单
        allMenu.forEach(menu -> {
            //加入map结构，方便查找
            mapping.put(menu.getId(),menu);
            //获取每一个遍历到的菜单的父id
            Long parentId = menu.getParentId();
            //父id为空  证明是一级菜单
            if (parentId==null){
                //一级菜单 直接加入
                firstLevel.add(menu);
            }
        });

        //第二次遍历  找出所有二级菜单  并找到对应的一级菜单  并把自己加入对方的children中
        allMenu.forEach(menu -> {
            Long parentId = menu.getParentId();
            //父id不为空 证明是二级菜单
            if (parentId!=null){
                //在暂存一级菜单的map中  根据父id找到对应的父菜单对象
                Menu parentMenu = mapping.get(parentId);
                if(parentMenu!=null) {
                    parentMenu.getChildren().add(menu);
                }
            }
        });

        return firstLevel;
    }
}

package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.pojo.RoleMenu;
import org.example.service.IRoleMenuService;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rolemenu")
public class RoleMenuController {

    @Autowired
    IRoleMenuService roleMenuService;

    /*
    给角色添加菜单：
      1、将该角色之前对应的菜单全部删除
         delete from role_menu where role_id=?
      2、批量添加

         roleId   1
         menuIds [21,22,41......]

         List<RoleMenu>  id   roleId  menuId
                        null   1       21
                        null   1       22
                        null   1       41
                        ......

     */
    @PostMapping("/add/{roleId}")
    public ResultBean addRoleMenus(@PathVariable("roleId") Long roleId, @RequestBody List<Long> menuIds) {

        boolean isRemove = roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("role_id", roleId));

        //创建要批量添加的角色菜单集合
        List<RoleMenu> roleMenuList = new ArrayList<>();

        menuIds.forEach(mId ->{
            roleMenuList.add(new RoleMenu(null,roleId,mId));
        });

        boolean b = roleMenuService.saveBatch(roleMenuList);

        return b?ResultBean.SUCCESS():ResultBean.ERROR("角色设置菜单失败");

    }

}

package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.pojo.Menu;
import org.example.query.MenuQuery;
import org.example.service.IMenuService;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyx
 * @since 2024-12-19
 */
@Api(description = "菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController<Menu, MenuQuery>{

    @Autowired
    IMenuService menuService;

    @ApiOperation(value = "通过id删除" )
    //  delete   /xxx/1003
    @PostMapping("/delete")   // http://localhost:8080/xxx/1003
    public ResultBean del(@RequestBody Menu menu){
        boolean b = baseService.removeById(menu.getId());
        if (!b){
            //抛异常
            throw new AppException(AppExceptionCodeMsg.DELETE_FAIL);
        }
        return ResultBean.SUCCESS();
    }

    @ApiOperation(value = "批量删除" )
    @PostMapping("/deleteBatch/{ids}")
    public ResultBean getBatchDel(@PathVariable("ids") List<Integer> list) throws Exception {
        //因为在前端的时候，直接变成19，21的形式了，在后端只需要进行转换即可
        boolean flg=menuService.removeByIds(list);
        if(!flg){
            throw new AppException(AppExceptionCodeMsg.SAVE_FAIL);
        }
        return ResultBean.SUCCESS();
    }

    @ApiOperation(value = "查询树结构菜单-联查")
    @PostMapping("/tree/{roleId}")
    public ResultBean treeMenu(@PathVariable("roleId") Long roleId){
        MenuQuery menuQuery = new MenuQuery();
        menuQuery.setRoleId(roleId);
        List<Menu> tree = menuService.tree(menuQuery.getQueryWrapper());
        return ResultBean.SUCCESS(tree);
    }

    @ApiOperation(value = "查询一级菜单")
    @GetMapping("/level1")
    public ResultBean firstLevelMenu(){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("parent_id");
        List<Menu> tree = menuService.list(queryWrapper);
        return ResultBean.SUCCESS(tree);
    }

    @ApiOperation(value = "查询所有菜单-单表")
    @GetMapping("/all/tree")
    public ResultBean all(){
        List<Menu> tree = menuService.tree(null);
        return ResultBean.SUCCESS(tree);
    }

}

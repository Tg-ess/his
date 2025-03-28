package org.example.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.mapper.PermissionMapper;
import org.example.pojo.Permission;
import org.example.query.PermissionQuery;
import org.example.service.IPermissionService;
import org.example.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyx
 * @since 2024-12-19
 */
@Api(description = "权限管理")
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController<Permission, PermissionQuery> {

    @Autowired
    IPermissionService permissionService;

    @ApiOperation("批量删除")
    @PostMapping("/batch1/{ids}")   // post + /xxx/batch
    public ResultBean batchDel(@PathVariable("ids") List<Integer> ids){
        System.out.println("----------BaseController---------batchDel---------------");
        boolean b = baseService.removeBatchByIds(ids);
        if (!b){
            //抛异常
            throw new AppException(AppExceptionCodeMsg.DELETE_FAIL);
        }
        return ResultBean.SUCCESS();
    }

    @ApiOperation("通过角色Id查询对应权限")
    @PostMapping("/role/{roleId}")
    public ResultBean selectByRoleId(@PathVariable("roleId") Long roleId ){
        if(roleId == null || roleId == 0){
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }
        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.setRoleId(roleId);
        List<Permission> permissions = permissionService.list(permissionQuery.getJoinWrapper());
        return ResultBean.SUCCESS(permissions);
    }

}

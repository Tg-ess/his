package org.example.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.pojo.Role;
import org.example.query.RoleQuery;
import org.example.vo.ResultBean;
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
@Api(description = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<Role, RoleQuery> {

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

}

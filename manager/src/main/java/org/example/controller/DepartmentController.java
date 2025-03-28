package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.pojo.Department;
import org.example.query.DepartmentQuery;
import org.example.service.IDepartmentService;
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
 * @since 2024-12-18
 */
@Api(value = "部门管理",description = "部门管理")
@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseController<Department, DepartmentQuery>{

    @Autowired
    IDepartmentService departmentService;

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

    @ApiOperation(value = "修改删除标记")
    @PutMapping("/change/delmark")
    public ResultBean changeDelmark(@RequestBody Department department){

        if(department==null ||
                department.getDelmark()==null ||
                department.getId()==null ||
                department.getId()==0){
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }

        UpdateWrapper<Department> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("delmark",department.getDelmark())
                     .eq("id",department.getId());

        boolean update = departmentService.update(updateWrapper);

        return update?ResultBean.SUCCESS():ResultBean.ERROR("修改部门删除状态失败");

    }

    @ApiOperation(value = "根据类型查询")
    @GetMapping("/type/{type}")
    public ResultBean getByType(@PathVariable("type") String deptType){
        List<Department> list = departmentService.list(new QueryWrapper<Department>().eq("dept_type",deptType));
        return ResultBean.SUCCESS(list);
    }

}

package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.exception.AppException;
import org.example.exception.AppExceptionCodeMsg;
import org.example.pojo.*;
import org.example.query.EmployeeQuery;
import org.example.service.IDepartmentService;
import org.example.service.IEmployeeService;
import org.example.service.IRoleService;
import org.example.service.ISchedulingService;
import org.example.utils.MD5Utils;
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
@Api(description = "员工管理")
@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController<Employee, EmployeeQuery> {

    @Autowired
    ISchedulingService schedulingService;

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IRoleService roleService;

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

    @PostMapping("/save")
    public ResultBean save(@RequestBody Employee employee) throws AppException {
        //这里要进行更新将其他的表的id进行填充

        //进行填充部门id
        QueryWrapper<Department> departmentQueryWrapper=new QueryWrapper<>();
        departmentQueryWrapper.eq("dept_name", employee.getDepartment().getDeptName());
        Department one = departmentService.getOne(departmentQueryWrapper);
        employee.setDeptmentId(one.getId());
        //进行填充角色id
        QueryWrapper<Role> roleQueryWrapper=new QueryWrapper<>();
        roleQueryWrapper.eq("name",employee.getRole().getName());
        Role one1 = roleService.getOne(roleQueryWrapper);
        employee.setRoleId(Math.toIntExact(one1.getId()));
 
        //接下来进行更新操作
        boolean save = employeeService.saveOrUpdate(employee);
        return save?ResultBean.SUCCESS():ResultBean.ERROR("保存员工信息失败");
//        boolean save = employeeService.save(employee);
//        return save? ResultBean.SUCCESS():ResultBean.ERROR("添加员工信息失败");
    }

    @GetMapping("/getEmp/{EmployeeId}")
    public ResultBean getEmp(@PathVariable("EmployeeId") Integer empId) throws AppException {
        //联查
        MPJLambdaWrapper<Employee> wrapper=new MPJLambdaWrapper<>();
        wrapper.setAlias("e")
                .selectAll(Employee.class,"e")
                .selectAs(Department::getDeptName,"'department.deptName'")
                .selectAs(RegistLevel::getRegistName,"'registLevel.registName'")
                .selectAs(Role::getName,"'role.name'")
                .leftJoin(Department.class,"d",Department::getId,Employee::getDeptmentId)
                .leftJoin(RegistLevel.class,"rl",RegistLevel::getId,Employee::getRegistLevelId)
                .leftJoin(Role.class,"re",Role::getId,Employee::getRoleId)
                .eq("e.id",empId);
        Employee employee=employeeService.getOne(wrapper);
        return employee==null?ResultBean.ERROR("员工信息不存在"):ResultBean.SUCCESS(employee);
    }

    @ApiOperation("根据条件查询-不分页不联查")
    @PostMapping("/conditions")
    public ResultBean getByConditions(@RequestBody EmployeeQuery employeeQuery){
        System.out.println(employeeQuery.getScheduleIndex()+"  "+employeeQuery.getDeptmentId()+"  "+employeeQuery.getRegistLevelId()+"  "+employeeQuery.getRoleName());

        //查到所有的排班信息
        List<Scheduling> schedulingList = schedulingService.list();
        //遍历排班信息
        schedulingList.forEach(scheduling -> {
            System.out.println(scheduling.getWeekRule());
            //传递的看诊时间对应的下标处 是不是1  如果是 记录这个排班的id值
            char c = scheduling.getWeekRule().charAt(employeeQuery.getScheduleIndex());
            if (c=='1'){
                employeeQuery.setScheduleId(scheduling.getId());
            }
        });

        //根据传递的角色名称 查询角色id
        Role role = roleService.getOne(new QueryWrapper<Role>().eq("name", employeeQuery.getRoleName()));
        employeeQuery.setRoleId(role.getId());

        //查询对应的医生
        List<Employee> list = employeeService.list(employeeQuery.getConditionWrapper());
        return ResultBean.SUCCESS(list);
    }

    @ApiOperation("重置密码")
    @PutMapping("/resetpass/{id}")
    public ResultBean resetPassword(@PathVariable("id") Integer employeeId){
        UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
        //密码加密  工具类的方法  传入明文返回密文
        String encrypt = MD5Utils.encrypt("123456");
        updateWrapper.set("password",encrypt);
        updateWrapper.eq("id",employeeId);

        boolean update = employeeService.update(updateWrapper);
        return update?ResultBean.SUCCESS():ResultBean.ERROR("重置密码失败");
    }

    @ApiOperation(value = "修改删除标记")
    @PutMapping("/change/delmark")
    public ResultBean changeDelmark(@RequestBody Employee employee){

        if(employee==null ||
                employee.getDelmark()==null ||
                employee.getId()==null ||
                employee.getId()==0){
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }

        UpdateWrapper<Department> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("delmark",employee.getDelmark())
                .eq("id",employee.getId());

        boolean update = departmentService.update(updateWrapper);

        return update?ResultBean.SUCCESS():ResultBean.ERROR("修改员工删除状态失败");

    }

    @GetMapping("/getby/{username}")
    public ResultBean getByUsername(@PathVariable("username") String username) {
        if(username == null || username == "") {
            throw new AppException(AppExceptionCodeMsg.PARAMS_EMPTY);
        }
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",username);
        Employee e = employeeService.getOne(queryWrapper);
        int roleId = e.getRoleId();
        return ResultBean.SUCCESS(roleId);
    }

}

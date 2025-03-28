package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;
import org.example.pojo.Department;
import org.example.pojo.Employee;
import org.example.pojo.RegistLevel;
import org.example.pojo.Role;

@Data
public class EmployeeQuery extends BaseQuery{

    private String realname;

    @Override
    public Wrapper getQueryWrapper() {

        MPJLambdaWrapper<Employee> wrapper = new MPJLambdaWrapper<>();
        wrapper.setAlias("e")
                .selectAll(Employee.class,"e")
                .selectAs(Department::getDeptName,"'department.deptName'")
                .selectAs(RegistLevel::getRegistName,"'registLevel.registName'")
                .selectAs(Role::getName,"'role.name'")
                .leftJoin(Department.class,"d",Department::getId,Employee::getDeptmentId)
                .leftJoin(RegistLevel.class,"r",RegistLevel::getId,Employee::getRegistLevelId)
                .leftJoin(Role.class,"ro",Role::getId,Employee::getRoleId)
                .orderByDesc("e.id");

        if(StringUtils.isNotEmpty(realname)){
            wrapper.like("e.realname",realname);
        }

        return wrapper;
    }

    private Integer scheduleIndex;
    private Integer scheduleId;
    private Integer deptmentId;
    private Integer registLevelId;
    private String roleName;
    private Long roleId;

    public Wrapper<Employee> getConditionWrapper(){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId)
                    .eq("deptment_id",deptmentId)
                    .eq("regist_level_id",registLevelId)
                    .eq("scheduling_id",scheduleId);
        return queryWrapper;
    }
}

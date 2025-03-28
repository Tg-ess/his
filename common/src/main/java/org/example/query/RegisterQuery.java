package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;
import org.example.pojo.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class RegisterQuery extends BaseQuery {

    private String caseNumber;
    private String realName;
    private Integer visitState;
    private String visitDate; //2024-12-26
    private Integer departmentId;

    private Integer employeeId;

    @Override
    public Wrapper getQueryWrapper() {

        MPJLambdaWrapper<Register> wrapper = new MPJLambdaWrapper<>();
        wrapper.setAlias("r")
                .selectAll(Register.class,"r")
                .selectAs(Department::getDeptName,"'department.deptName'")
                .selectAs(Employee::getRealname,"'employee.realname'")
                .selectAs(SettleCategory::getSettleName,"'settleCategory.settleName'")
                .selectAs(RegistLevel::getRegistName,"'registLevel.registName'")
                .leftJoin(Department.class,"d",Department::getId,Register::getDeptmentId)
                .leftJoin(Employee.class,"e",Employee::getId,Register::getEmployeeId)
                .leftJoin(SettleCategory.class,"s",SettleCategory::getId,Register::getSettleCategoryId)
                .leftJoin(RegistLevel.class,"rl",RegistLevel::getId,Register::getRegistLevelId)
                .orderByDesc("r.id");

        if (StringUtils.isNotEmpty(realName)){
            wrapper.eq("real_name",realName);
        }

        if (StringUtils.isNotEmpty(caseNumber)){
            wrapper.eq("case_number",caseNumber);
        }

        if (visitState!=null){
            wrapper.eq("visit_state",visitState);
        }

        //DATE(visit_date)  可以获取日期时间中的日期   2024-12-26 08:00:00  中的 2024-12-26取出来
        if (StringUtils.isNotEmpty(visitDate)){
            wrapper.eq("DATE(visit_date)",visitDate);
        }

        if (departmentId!=null){
            wrapper.eq("r.deptment_id",departmentId); //科室
        }

        if(employeeId!=null){
            wrapper.eq("r.employee_id",employeeId);  //医生
        }


        return wrapper;
    }

    public Wrapper getByCondition(){
        QueryWrapper<Register> wrapper = new QueryWrapper<>();
        List<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(2);
        num.add(3);
        wrapper.eq("employee_id",employeeId)
                .in("visit_state",num);

        return wrapper;
    }


}

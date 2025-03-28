package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pojo.Department;
import org.example.pojo.Employee;
import org.example.pojo.RegistLevel;
import org.example.pojo.Role;

@Data
@NoArgsConstructor
public class DepartmentQuery extends BaseQuery{

    private String deptCode;
    private String deptName;
    private String deptType;


    @Override
    public Wrapper getQueryWrapper() {

        MPJLambdaWrapper<Department> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Department.class)
                .orderByDesc("id");
        if(StringUtils.isNotEmpty(deptCode)){
            wrapper.like("dept_code",deptCode);
        }
        if(StringUtils.isNotEmpty(deptName)){
            wrapper.like("dept_name",deptName);
        }
        if(StringUtils.isNotEmpty(deptType)){
            wrapper.like("dept_type",deptType);
        }

        return wrapper;
    }

}

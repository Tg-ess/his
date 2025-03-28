package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pojo.Role;

@Data
@NoArgsConstructor
public class RoleQuery extends BaseQuery{

    private String name;
    private String sn;

    @Override
    public Wrapper getQueryWrapper() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(StringUtils.isNotEmpty(sn)){
            queryWrapper.like("sn",sn);
        }

        return queryWrapper;
    }
}

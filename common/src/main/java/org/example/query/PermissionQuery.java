package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pojo.Menu;
import org.example.pojo.Permission;

@Data
@NoArgsConstructor
public class PermissionQuery extends BaseQuery {

    private Long roleId;

    /**
     * permission表和role_permission表联查，通过角色Id查询权限
     * @return
     */
    public Wrapper getJoinWrapper(){

        MPJLambdaWrapper<Menu> wrapper = new MPJLambdaWrapper<>();
        wrapper.setAlias("p")
                .selectAll(Permission.class)
                .leftJoin(" role_permission rp on p.id=rp.permission_id");
        if(roleId != null && roleId != 0){
            wrapper.eq("rp.role_id",roleId);
        }

        return wrapper;
    }

    private Integer id;
    private String name;

    @Override
    public Wrapper getQueryWrapper() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if(StringUtils.isNotEmpty(name)){
            queryWrapper.like("name",name);
        }
        return queryWrapper;
    }
}

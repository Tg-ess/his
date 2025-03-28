package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pojo.Menu;

@Data
@NoArgsConstructor
public class MenuQuery extends BaseQuery {

    private Long roleId;

    @Override
    public Wrapper getQueryWrapper() {

        MPJLambdaWrapper<Menu> wrapper = new MPJLambdaWrapper<>();
        wrapper.setAlias("m")
                .selectAll(Menu.class,"m")
                .leftJoin(" role_menu rm ON m.id=rm.menu_id");
        if(roleId != null && roleId != 0){
            wrapper.eq("rm.role_Id",roleId);
        }

        return wrapper;
    }
}

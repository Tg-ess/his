package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页条件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseQuery {

    private Integer current = 1; //当前页页码
    private Integer size = 5; //每页显示的记录数

    public Wrapper getQueryWrapper(){
        return new QueryWrapper();
    }

}

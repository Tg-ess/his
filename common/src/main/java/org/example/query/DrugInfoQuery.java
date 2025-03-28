package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;
import org.example.pojo.DrugInfo;

@Data
public class DrugInfoQuery extends BaseQuery{

    private String drugName;

    @Override
    public Wrapper getQueryWrapper() {
        QueryWrapper<DrugInfo> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(drugName)){
            queryWrapper.like("drug_name",drugName);
        }

        return queryWrapper;
    }
}

package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pojo.Disease;

@Data
public class DiseaseQuery extends BaseQuery {

    private String diseaseCode;
    private String diseaseName;

    @Override
    public Wrapper getQueryWrapper() {

        QueryWrapper<Disease> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(diseaseCode)){
            queryWrapper.eq("disease_code",diseaseCode);
        }
        if(StringUtils.isNotEmpty(diseaseName)){
            queryWrapper.eq("disease_name",diseaseName);
        }

        return queryWrapper;
    }
}

package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import org.example.pojo.MedicalRecordDisease;

@Data
public class RecordDiseaseQuery extends BaseQuery{

    private Integer medicalRecordId;
    private Integer diseaseId;

    @Override
    public Wrapper getQueryWrapper() {
        QueryWrapper<MedicalRecordDisease> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("medical_record_id",medicalRecordId);
        queryWrapper.eq("disease_id",diseaseId);

        return queryWrapper;
    }
}

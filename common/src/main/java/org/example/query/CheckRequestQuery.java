package org.example.query;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;
import org.example.pojo.CheckRequest;
import org.example.pojo.MedicalTechnology;
import org.example.pojo.Register;

@Data
public class CheckRequestQuery extends BaseQuery {

    private String caseNumber;
    private Integer registerId;
    private String checkState;

    public  Wrapper getQueryWrapper(){
        MPJLambdaWrapper<CheckRequest> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.setAlias("cr")
                .selectAll(CheckRequest.class,"cr")
                .selectAs(MedicalTechnology::getTechName,"'medicalTechnology.techName'")
                .selectAs(MedicalTechnology::getTechPrice,"'medicalTechnology.techPrice'")
                .leftJoin(MedicalTechnology.class,"m",MedicalTechnology::getId,CheckRequest::getMedicalTechnologyId)
                .leftJoin(Register.class,"r",Register::getId,CheckRequest::getRegisterId);

        if(registerId!=null && registerId!=0){
            queryWrapper.eq("register_id",registerId);
        }
        if(StringUtils.isNotEmpty(checkState)){
            queryWrapper.eq("check_state",checkState);
        }
        if(StringUtils.isNotEmpty(caseNumber)){
            queryWrapper.eq("r.case_number",caseNumber);
        }

        return queryWrapper;
    }
}

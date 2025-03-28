package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;
import org.example.pojo.DrugInfo;
import org.example.pojo.Prescription;

@Data
public class PrescriptionQuery extends BaseQuery{

    private Integer registerId;

    public Wrapper getJoinQueryWrapper(){
        MPJLambdaWrapper<Prescription> wrapper = new MPJLambdaWrapper<>();
        wrapper.setAlias("p")
                .selectAll(Prescription.class)
                .selectAs(DrugInfo::getDrugName,"'drugInfo.drugName'")
                .selectAs(DrugInfo::getDrugFormat,"'drugInfo.drugFormat'")
                .selectAs(DrugInfo::getDrugPrice,"'drugInfo.drugPrice'")
                .leftJoin(DrugInfo.class,"d",DrugInfo::getId,Prescription::getDrugId)
                .eq(registerId!=null && registerId!=0,"register_id",registerId);

        return wrapper;
    }

}

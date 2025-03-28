package org.example.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.Data;
import org.example.pojo.Cost;
import org.example.pojo.Register;

import java.util.List;

@Data
public class CostQuery extends BaseQuery{

    private String caseNumber;
    private String realName;
    private Integer registerId;
    private String createtime;
    private Integer id;
    private Integer refId;

    /*
    费用表和挂号表联查
     */
    @Override
    public Wrapper getQueryWrapper() {
        MPJLambdaWrapper<Cost> wrapper = new MPJLambdaWrapper<>();
        wrapper.setAlias("c")
                .selectAll(Cost.class,"c")
                .selectAs(Register::getCaseNumber,"'register.caseNumber'")
                .selectAs(Register::getRealName,"'register.realName'")
                .selectAs(Register::getCardNumber,"'register.cardNumber'")
                .leftJoin(Register.class,"r",Register::getId,Cost::getRegisterId)
                .orderByDesc("c.id");
        if(StringUtils.isNotEmpty(caseNumber)){
            wrapper.eq("r.case_number",caseNumber);
        }
        if(StringUtils.isNotEmpty(realName)){
            wrapper.eq("r.real_name",realName);
        }
        //DATE(visit_date)  可以获取日期时间中的日期   2024-12-26 08:00:00  中的 2024-12-26取出来
        if (StringUtils.isNotEmpty(createtime)){
            wrapper.eq("DATE(createtime)",createtime);
        }
        if(state != null){
            wrapper.eq("state",state);
        }
        if(id != null){
            wrapper.eq("c.id",id);
        }

        return wrapper;
    }

    private String payMethod;
    private Integer settleCategoryId;
    private Integer state;
    private List<Integer> ids;

    public UpdateWrapper<Cost> getUpdateWrapper(){
        UpdateWrapper<Cost> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(StringUtils.isNotEmpty(payMethod),"pay_method",payMethod)
                    .set(settleCategoryId!=null,"settle_category_id",settleCategoryId)
                    .set(state!=null,"state",state)
                    .in("id",ids);

        return updateWrapper;
    }

    public UpdateWrapper<Cost> getRefundWrapper() {
        UpdateWrapper<Cost> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("state",state)
                     .set("settle_category_id",settleCategoryId)
                     .eq("id",id);

        return updateWrapper;
    }
}

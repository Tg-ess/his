package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyx
 * @since 2024-12-18
 */
@TableName("check_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("register_id")
    private Integer registerId;

    private Integer medicalTechnologyId;

    private String checkInfo;

    private String checkPosition;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date creationTime;

    /**
     * 查检医生id
     */
    private Integer checkEmployeeId;

    /**
     * 结果输入医生id
     */
    private Integer inputcheckEmployeeId;

    /**
     * 检查时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    private Date checkTime;

    /**
     * 结果
     */
    private String checkResult;

    /**
     * 状态(已开立,已缴费,执行完成,已出结果,已退费,已作废)
     */
    private String checkState;

    private String checkRemark;

    @TableField(exist = false)
    private MedicalTechnology medicalTechnology;


}

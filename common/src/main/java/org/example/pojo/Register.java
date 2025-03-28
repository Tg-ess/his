package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyx
 * @since 2024-12-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Register implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 病历号
     */
    private String caseNumber;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 性别（男、女）
     */
    private String gender;

    /**
     * 身份证号
     */
    private String cardNumber;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 年龄类型（年、天）
     */
    private String ageType;

    /**
     * 家庭住址
     */
    private String homeAddress;

    /**
     * 本次看诊日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    private Date visitDate;

    /**
     * 午别（上午、下午）
     */
    private String noon;

    /**
     * 本次挂号科室id
     */
    private Integer deptmentId;

    /**
     * 本次挂号医生id
     */
    private Integer employeeId;

    /**
     * 本次挂号级别id
     */
    private Integer registLevelId;

    /**
     * 结算类别id
     */
    private Integer settleCategoryId;

    /**
     * 病历本要否（要、否）
     */
    private String isBook;

    /**
     * 收费方式（现金、银行卡、微信、医保卡、支付宝）
     */
    private String registMethod;

    /**
     * 本次看诊状态（1-已挂号
2-医生接诊
3-看诊结束
4-已退号）
     */
    private Integer visitState;

    /**
     * 挂号金额
     */
    private BigDecimal registMoney;

    @TableField(exist = false)
    private Employee employee; //看诊医生

    @TableField(exist = false)
    private Department department;//挂号科室

    @TableField(exist = false)
    private SettleCategory settleCategory;//结算类别

    @TableField(exist = false)
    private RegistLevel registLevel;//挂号类别


}

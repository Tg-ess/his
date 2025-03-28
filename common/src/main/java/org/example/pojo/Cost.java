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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author hxj
 * @since 2024-12-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cost implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联的挂号id
     */
    private Integer registerId;

    /**
     * 项目名称
     */
    private String costname;

    /*
    关联的检查表的id
         检验表的id
         处置表的id
         药品表的id
     */
    private Integer refId;

    /**
     * 类型(检查、检验、处置、药品)
     */
    private String costtype;

    /**
     * 价格
     */
    private BigDecimal costprice;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;

    /**
     * 状态(0 未支付 1 已支付)
     */
    private Integer state;

    /**
     * 支付方式(现金、医保卡、银联卡、微信、支付宝)
     */
    private String payMethod;

    /**
     * 结算方式
     */
    private Integer settleCategoryId;

    @TableField(exist = false)
    private Register register;

    @TableField(exist = false)
    private SettleCategory settleCategory;
}
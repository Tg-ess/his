package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyx
 * @since 2024-12-18
 */
@TableName("regist_level")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 号别编码
     */
    private String registCode;

    /**
     * 号别名称
     */
    private String registName;

    /**
     * 挂号费
     */
    private BigDecimal registFee;

    /**
     * 挂号限额
     */
    private Integer registQuota;

    /**
     * 显示顺序号
     */
    private Integer sequenceNo;

    /**
     * 删除标记
     */
    private Integer delmark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRegistCode() {
        return registCode;
    }

    public void setRegistCode(String registCode) {
        this.registCode = registCode;
    }
    public String getRegistName() {
        return registName;
    }

    public void setRegistName(String registName) {
        this.registName = registName;
    }
    public BigDecimal getRegistFee() {
        return registFee;
    }

    public void setRegistFee(BigDecimal registFee) {
        this.registFee = registFee;
    }
    public Integer getRegistQuota() {
        return registQuota;
    }

    public void setRegistQuota(Integer registQuota) {
        this.registQuota = registQuota;
    }
    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
    public Integer getDelmark() {
        return delmark;
    }

    public void setDelmark(Integer delmark) {
        this.delmark = delmark;
    }

    @Override
    public String toString() {
        return "RegistLevel{" +
            "id=" + id +
            ", registCode=" + registCode +
            ", registName=" + registName +
            ", registFee=" + registFee +
            ", registQuota=" + registQuota +
            ", sequenceNo=" + sequenceNo +
            ", delmark=" + delmark +
        "}";
    }
}

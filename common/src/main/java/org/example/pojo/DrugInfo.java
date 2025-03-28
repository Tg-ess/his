package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyx
 * @since 2024-12-18
 */
@TableName("drug_info")
public class DrugInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品药编码（国家药品编码本位码共14位“86”，代表在我国境内生产、销售的所有药品；国家药品编码本位码类别码为“9”，代表药品；国家药品编码本位码本体码的前5位为药品企业标识）
     */
    private String drugCode;

    /**
     * 药品名称
     */
    private String drugName;

    /**
     * 品药规格
     */
    private String drugFormat;

    /**
     * 包装单位
     */
    private String drugUnit;

    /**
     * 生产厂家
     */
    private String manufacturer;

    /**
     * 药剂类型
     */
    private String drugDosage;

    /**
     * 药品类型
     */
    private String drugType;

    /**
     * 药品单价
     */
    private BigDecimal drugPrice;

    /**
     * 拼音助记码
     */
    private String mnemonicCode;

    /**
     * 创建时间
     */
    private LocalDate creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }
    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
    public String getDrugFormat() {
        return drugFormat;
    }

    public void setDrugFormat(String drugFormat) {
        this.drugFormat = drugFormat;
    }
    public String getDrugUnit() {
        return drugUnit;
    }

    public void setDrugUnit(String drugUnit) {
        this.drugUnit = drugUnit;
    }
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getDrugDosage() {
        return drugDosage;
    }

    public void setDrugDosage(String drugDosage) {
        this.drugDosage = drugDosage;
    }
    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }
    public BigDecimal getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(BigDecimal drugPrice) {
        this.drugPrice = drugPrice;
    }
    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "DrugInfo{" +
            "id=" + id +
            ", drugCode=" + drugCode +
            ", drugName=" + drugName +
            ", drugFormat=" + drugFormat +
            ", drugUnit=" + drugUnit +
            ", manufacturer=" + manufacturer +
            ", drugDosage=" + drugDosage +
            ", drugType=" + drugType +
            ", drugPrice=" + drugPrice +
            ", mnemonicCode=" + mnemonicCode +
            ", creationDate=" + creationDate +
        "}";
    }
}

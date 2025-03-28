package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author cyx
 * @since 2024-12-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("medical_technology")
public class MedicalTechnology implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目编码
     */
    private String techCode;

    /**
     * 项目名称
     */
    private String techName;

    /**
     * 规格
     */
    private String techFormat;

    private BigDecimal techPrice;

    private String techType;

    private String priceType;

    private Integer deptmentId;
}

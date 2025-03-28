package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

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
public class Disease implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 疾病助记编码
     */
    @TableField("diseaseICD")
    private String diseaseCode;

    /**
     * 疾病名称
     */
    private String diseaseName;

    /**
     * 国际ICD编码
     */
    @TableField("disease_code")
    private String diseaseICD;

    /**
     * 疾病所属分类
     */
    private String diseaseCategory;

}

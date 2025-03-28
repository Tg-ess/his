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

/**
 * <p>
 * 
 * </p>
 *
 * @author cyx
 * @since 2024-12-18
 */
@TableName("medical_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联挂号表id
     */
    private Integer registerId;

    /**
     * 主诉
     */
    private String readme;

    /**
     * 现病史
     */
    private String present;

    /**
     * 现病治疗情况
     */
    private String presentTreat;

    /**
     * 既往史
     */
    private String history;

    /**
     * 过敏史
     */
    private String allergy;

    /**
     * 体格检查
     */
    private String physique;

    /**
     * 检查/检验建议
     */
    private String proposal;

    /**
     * 注意事项
     */
    private String careful;

    /**
     * 断诊结果
     */
    private String diagnosis;

    /**
     * 理处意见
     */
    private String cure;

}

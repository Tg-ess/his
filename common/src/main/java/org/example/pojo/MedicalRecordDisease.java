package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("medical_record_disease")
public class MedicalRecordDisease implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 病历id
     */
    private Integer medicalRecordId;

    /**
     * 疾病id
     */
    private Integer diseaseId;

    @TableField(exist = false)
    private Disease disease;


}

package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
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
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 挂号id
     */
    private Integer registerId;

    /**
     * 药品id
     */
    private Integer drugId;

    /**
     * 用法用量和频次
     */
    private String drugUsage;

    /**
     * 数量
     */
    private Integer drugNumber;

    /**
     * 开立时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date creationTime;

    /**
     * 状态(已开立、已缴费、已发药、已退药、已退费)
     */
    private String drugState;

    @TableField(exist = false)
    private DrugInfo drugInfo;
}

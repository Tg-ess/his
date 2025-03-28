package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyx
 * @since 2024-12-18
 */
public class Scheduling implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 排班说明
     */
    private String ruleName;

    /**
     * 班排规则14位01组成字符串，从星期日开始
     */
    private String weekRule;

    /**
     * 生效标记
     */
    private Integer delmark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public String getWeekRule() {
        return weekRule;
    }

    public void setWeekRule(String weekRule) {
        this.weekRule = weekRule;
    }
    public Integer getDelmark() {
        return delmark;
    }

    public void setDelmark(Integer delmark) {
        this.delmark = delmark;
    }

    @Override
    public String toString() {
        return "Scheduling{" +
            "id=" + id +
            ", ruleName=" + ruleName +
            ", weekRule=" + weekRule +
            ", delmark=" + delmark +
        "}";
    }
}

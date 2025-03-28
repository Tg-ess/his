package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyx
 * @since 2024-12-18
 */
@TableName("inspection_request")
public class InspectionRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 挂号id
     */
    private Integer registerId;

    /**
     * åŒ»æŠ€é¡¹ç›®id
     */
    private Integer medicalTechnologyId;

    /**
     * 目的要求
     */
    private String inspectionInfo;

    /**
     * 检查部位
     */
    private String inspectionPosition;

    /**
     * 开立时间
     */
    private LocalDateTime creationTime;

    /**
     * 检验人员id
     */
    private Integer inspectionEmployeeId;

    /**
     * 结果录入人员id
     */
    private Integer inputinspectionEmployeeId;

    /**
     * 检验时间
     */
    private LocalDateTime inspectionTime;

    /**
     * 检验结果
     */
    private String inspectionResult;

    /**
     * 状态(已开立,已缴费,执行完成,已出结果,已退费,已作废)
     */
    private String inspectionState;

    /**
     * 备注
     */
    private String inspectionRemark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }
    public Integer getMedicalTechnologyId() {
        return medicalTechnologyId;
    }

    public void setMedicalTechnologyId(Integer medicalTechnologyId) {
        this.medicalTechnologyId = medicalTechnologyId;
    }
    public String getInspectionInfo() {
        return inspectionInfo;
    }

    public void setInspectionInfo(String inspectionInfo) {
        this.inspectionInfo = inspectionInfo;
    }
    public String getInspectionPosition() {
        return inspectionPosition;
    }

    public void setInspectionPosition(String inspectionPosition) {
        this.inspectionPosition = inspectionPosition;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
    public Integer getInspectionEmployeeId() {
        return inspectionEmployeeId;
    }

    public void setInspectionEmployeeId(Integer inspectionEmployeeId) {
        this.inspectionEmployeeId = inspectionEmployeeId;
    }
    public Integer getInputinspectionEmployeeId() {
        return inputinspectionEmployeeId;
    }

    public void setInputinspectionEmployeeId(Integer inputinspectionEmployeeId) {
        this.inputinspectionEmployeeId = inputinspectionEmployeeId;
    }
    public LocalDateTime getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(LocalDateTime inspectionTime) {
        this.inspectionTime = inspectionTime;
    }
    public String getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(String inspectionResult) {
        this.inspectionResult = inspectionResult;
    }
    public String getInspectionState() {
        return inspectionState;
    }

    public void setInspectionState(String inspectionState) {
        this.inspectionState = inspectionState;
    }
    public String getInspectionRemark() {
        return inspectionRemark;
    }

    public void setInspectionRemark(String inspectionRemark) {
        this.inspectionRemark = inspectionRemark;
    }

    @Override
    public String toString() {
        return "InspectionRequest{" +
            "id=" + id +
            ", registerId=" + registerId +
            ", medicalTechnologyId=" + medicalTechnologyId +
            ", inspectionInfo=" + inspectionInfo +
            ", inspectionPosition=" + inspectionPosition +
            ", creationTime=" + creationTime +
            ", inspectionEmployeeId=" + inspectionEmployeeId +
            ", inputinspectionEmployeeId=" + inputinspectionEmployeeId +
            ", inspectionTime=" + inspectionTime +
            ", inspectionResult=" + inspectionResult +
            ", inspectionState=" + inspectionState +
            ", inspectionRemark=" + inspectionRemark +
        "}";
    }
}

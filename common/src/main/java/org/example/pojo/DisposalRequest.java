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
@TableName("disposal_request")
public class DisposalRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer registerId;

    private Integer medicalTechnologyId;

    private String disposalInfo;

    private String disposalPosition;

    private LocalDateTime creationTime;

    private Integer disposalEmployeeId;

    private Integer inputdisposalEmployeeId;

    private LocalDateTime disposalTime;

    private String disposalResult;

    private String disposalState;

    private String disposalRemark;

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
    public String getDisposalInfo() {
        return disposalInfo;
    }

    public void setDisposalInfo(String disposalInfo) {
        this.disposalInfo = disposalInfo;
    }
    public String getDisposalPosition() {
        return disposalPosition;
    }

    public void setDisposalPosition(String disposalPosition) {
        this.disposalPosition = disposalPosition;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
    public Integer getDisposalEmployeeId() {
        return disposalEmployeeId;
    }

    public void setDisposalEmployeeId(Integer disposalEmployeeId) {
        this.disposalEmployeeId = disposalEmployeeId;
    }
    public Integer getInputdisposalEmployeeId() {
        return inputdisposalEmployeeId;
    }

    public void setInputdisposalEmployeeId(Integer inputdisposalEmployeeId) {
        this.inputdisposalEmployeeId = inputdisposalEmployeeId;
    }
    public LocalDateTime getDisposalTime() {
        return disposalTime;
    }

    public void setDisposalTime(LocalDateTime disposalTime) {
        this.disposalTime = disposalTime;
    }
    public String getDisposalResult() {
        return disposalResult;
    }

    public void setDisposalResult(String disposalResult) {
        this.disposalResult = disposalResult;
    }
    public String getDisposalState() {
        return disposalState;
    }

    public void setDisposalState(String disposalState) {
        this.disposalState = disposalState;
    }
    public String getDisposalRemark() {
        return disposalRemark;
    }

    public void setDisposalRemark(String disposalRemark) {
        this.disposalRemark = disposalRemark;
    }

    @Override
    public String toString() {
        return "DisposalRequest{" +
            "id=" + id +
            ", registerId=" + registerId +
            ", medicalTechnologyId=" + medicalTechnologyId +
            ", disposalInfo=" + disposalInfo +
            ", disposalPosition=" + disposalPosition +
            ", creationTime=" + creationTime +
            ", disposalEmployeeId=" + disposalEmployeeId +
            ", inputdisposalEmployeeId=" + inputdisposalEmployeeId +
            ", disposalTime=" + disposalTime +
            ", disposalResult=" + disposalResult +
            ", disposalState=" + disposalState +
            ", disposalRemark=" + disposalRemark +
        "}";
    }
}

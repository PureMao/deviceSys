package cn.haoxy.model;

import java.io.Serializable;

/**
 * @author 
 */
public class QrRecord implements Serializable {
    private Integer id;

    /**
     * 扫码记录ID
     */
    private Integer excelId;

    /**
     * 设备序列号
     */
    private String deviceNo;

    /**
     * 设备被检出 1是,-1否
     */
    private Integer isLocalDevice;

    /**
     * 删除标记
     */
    private Integer deleteMark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExcelId() {
        return excelId;
    }

    public void setExcelId(Integer excelId) {
        this.excelId = excelId;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getIsLocalDevice() {
        return isLocalDevice;
    }

    public void setIsLocalDevice(Integer isLocalDevice) {
        this.isLocalDevice = isLocalDevice;
    }

    public Integer getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(Integer deleteMark) {
        this.deleteMark = deleteMark;
    }
}
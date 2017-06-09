package cn.haoxy.model;

import java.io.Serializable;

public class DeviceCheckRecordVo implements Serializable {

	private static final long serialVersionUID = 8962770726195827410L;

	private Integer deviceId;

	private String deviceName;

	private Integer deviceTypeId;

	private String deviceType;

	private Integer devicePlaceId;

	private String devicePlace;

	private String deviceNo;

	private Long checkTime;
	
	private String cTime;

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	private String remark;

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Integer getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getDevicePlaceId() {
		return devicePlaceId;
	}

	public void setDevicePlaceId(Integer devicePlaceId) {
		this.devicePlaceId = devicePlaceId;
	}

	public String getDevicePlace() {
		return devicePlace;
	}

	public void setDevicePlace(String devicePlace) {
		this.devicePlace = devicePlace;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public Long getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

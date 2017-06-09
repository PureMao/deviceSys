package cn.haoxy.model;

import java.io.Serializable;
import java.sql.Date;

public class DeviceCheckOverTimeVo implements Serializable {

	private static final long serialVersionUID = 1101631450501178629L;
	
	private Integer deviceId;
	
	private String deviceNo;
	
	private Date lastCheckTime;

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public Date getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(Date lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

}

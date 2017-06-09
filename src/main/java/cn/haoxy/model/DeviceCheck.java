package cn.haoxy.model;

import java.io.Serializable;

/**
 * @author
 */
public class DeviceCheck implements Serializable {

	private static final long serialVersionUID = -539223864769394642L;

	private Integer id;

	/**
	 * 设备ID
	 */
	private Integer deviceId;

	/**
	 * 检查人ID
	 */
	private Integer checkPersonId;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 设备状况
	 */
	private String status;

	/**
	 * 检查时间
	 */
	private Long checkTime;

	/**
	 * 删除标记 大于0表示已经删除
	 */
	private Integer deleteMark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getCheckPersonId() {
		return checkPersonId;
	}

	public void setCheckPersonId(Integer checkPersonId) {
		this.checkPersonId = checkPersonId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}
}
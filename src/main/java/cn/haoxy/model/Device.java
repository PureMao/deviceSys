package cn.haoxy.model;

import java.io.Serializable;

/**
 * @author
 */
public class Device implements Serializable {

	private static final long serialVersionUID = -2883786325313915696L;

	private Integer id;

	/**
	 * 设备类型id
	 */
	private Integer typeId;

	private String name;

	private String type;

	private String unit;

	private String model;

	private String maker;

	/**
	 * 存放位置id
	 */
	private Integer placeId;

	private String place;

	/**
	 * 设备地点id
	 */
	private Integer locationId;

	private String location;

	/**
	 * 设备编号
	 */
	private String deviceNo;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 入场时间
	 */
	private Long registerTime;

	/**
	 * 废报日期
	 */
	private Long quitTime;

	/**
	 * 报废标记 大于0表示已经报废
	 */
	private Integer quitMark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Long registerTime) {
		this.registerTime = registerTime;
	}

	public Long getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(Long quitTime) {
		this.quitTime = quitTime;
	}

	public Integer getQuitMark() {
		return quitMark;
	}

	public void setQuitMark(Integer quitMark) {
		this.quitMark = quitMark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", typeId=" + typeId + ", placeId=" + placeId + ", locationId=" + locationId
				+ ", deviceNo=" + deviceNo + ", remark=" + remark + "]";
	}
	
	

}
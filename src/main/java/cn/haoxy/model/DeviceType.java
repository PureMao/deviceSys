package cn.haoxy.model;

import java.io.Serializable;

/**
 * @author
 */
public class DeviceType implements Serializable {

	private static final long serialVersionUID = -4200637288951750845L;

	private Integer id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 型号
	 */
	private String model;

	/**
	 * 单位
	 */
	private String unit;

	/**
	 * 设备类型
	 */
	private String type;

	/**
	 * 设备厂家
	 */
	private String maker;

	/**
	 * 删除标记
	 */
	private Integer deleteMark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Integer getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}
}
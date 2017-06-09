package cn.haoxy.model;

import java.io.Serializable;

public class DeviceGroupCount implements Serializable {

	private static final long serialVersionUID = 8455246012075865889L;

	private Integer number;

	private Integer typeId;

	private String type;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

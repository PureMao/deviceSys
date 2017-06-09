package cn.haoxy.model;

import java.io.Serializable;

public class DeviceDistribution implements Serializable {

	private static final long serialVersionUID = 3454119367393755286L;

	private Integer locationId;

	private String location;

	private Integer number;

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}

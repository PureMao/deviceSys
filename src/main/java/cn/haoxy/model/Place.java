package cn.haoxy.model;

import java.io.Serializable;

/**
 * @author
 */
public class Place implements Serializable {

	private static final long serialVersionUID = 4366759131731704558L;

	private Integer id;

	/**
	 * 存放位置
	 */
	private String place;

	/**
	 * 所属工区id
	 */
	private Integer locationId;

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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}
}
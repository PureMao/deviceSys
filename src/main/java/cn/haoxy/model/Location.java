package cn.haoxy.model;

import java.io.Serializable;

/**
 * @author
 */
public class Location implements Serializable {

	private static final long serialVersionUID = 8034210005325884991L;

	private Integer id;

	private String location;

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}
}
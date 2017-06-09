package cn.haoxy.model;

import java.io.Serializable;

public class QrVo implements Serializable {

	private static final long serialVersionUID = -3294304456743114533L;

	private Integer id;

	private Integer excelId;

	private String userName;

	private Integer flag;

	private Long dateTime;

	private String dateStr;

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Long getDateTime() {
		return dateTime;
	}

	public void setDateTime(Long dateTime) {
		this.dateTime = dateTime;
	}

}

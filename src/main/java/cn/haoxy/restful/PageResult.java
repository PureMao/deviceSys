package cn.haoxy.restful;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = 8412711824107894765L;

	/**
	 * min 1
	 */
	private int pageSize;

	/**
	 * from 0 begin
	 */
	private int pageNo;

	private int total;

	private int totalPageNum;

	private List<T> context;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public List<T> getContext() {
		return context;
	}

	public void setContext(List<T> context) {
		this.context = context;
	}

}

package com.http;

/**
 * 分页Bean,所有涉及分页的接口都必须包含该对象
 * 
 * @author binshenchen
 *
 */
public class Page {
	// 当前页数（从1开始）
	private int currentPage;
	// 每页条数
	private int pageRecords;
	// 总页数
	private int totalPages;
	// 总记录条数
	private int totalRecords;
	// 本次查询开始记录
	private int startRecord;
	// 下一页
	private int nextPage;
	// 上一页
	private int previousPage;
	// 是否有下一页
	private boolean hasNextPage;
	// 是否有上一页
	private boolean hasPreviousPage;
	
	/**
	 * Default Construct
	 */
	public Page(){
		
	}
	
	/**
	 * 带参构造器
	 */
	public Page(int currentPage, int pageRecords, int totalPages,
			int totalRecords, int startRecord, int nextPage, int previousPage,
			boolean hasNextPage, boolean hasPreviousPage) {
		super();
		this.currentPage = currentPage;
		this.pageRecords = pageRecords;
		this.totalPages = totalPages;
		this.totalRecords = totalRecords;
		this.startRecord = startRecord;
		this.nextPage = nextPage;
		this.previousPage = previousPage;
		this.hasNextPage = hasNextPage;
		this.hasPreviousPage = hasPreviousPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageRecords() {
		return pageRecords;
	}
	public void setPageRecords(int pageRecords) {
		this.pageRecords = pageRecords;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getStartRecord() {
		return startRecord;
	}
	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	
}

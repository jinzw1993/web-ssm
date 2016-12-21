package com.heitian.ssm.bo;

public class PageCondition {
	
	private int page =1;
    private int count = 30;
    private int start;
    private Long status;
    private Long processStatus;
    
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = (page-1)*count;
    }

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Long processStatus) {
		this.processStatus = processStatus;
	}

}

package com.viglet.turing.client.sn;

import java.util.Date;

/**
 * Create a query and specify between dates.
 * 
 * @since 0.3.4
 */
public class TurSNClientBetweenDates {
	
	private String field;
	
	private Date startDate;
	
	private Date endDate;

	public TurSNClientBetweenDates(String field, Date startDate, Date endDate) {
		this.setField(field);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}

	public String getField() {
		return field;
	}


	public void setField(String field) {
		this.field = field;
	}


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}

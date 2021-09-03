package com.viglet.turing.client.sn;

/**
 * Specify and retrieve Turing AI sort.
 * 
 * @since 0.3.4
 */
public class TurSNSortField {

	private String field;
	private TurSNQuery.ORDER sort;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public TurSNQuery.ORDER getSort() {
		return sort;
	}
	public void setSort(TurSNQuery.ORDER sort) {
		this.sort = sort;
	}
	
	
}

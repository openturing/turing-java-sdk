package com.viglet.turing.client;

public class TuringSortField {

	private String field;
	private TuringQuery.ORDER sort;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public TuringQuery.ORDER getSort() {
		return sort;
	}
	public void setSort(TuringQuery.ORDER sort) {
		this.sort = sort;
	}
	
	
}

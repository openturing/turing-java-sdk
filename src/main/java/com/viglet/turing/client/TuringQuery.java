package com.viglet.turing.client;

public class TuringQuery {
	public enum ORDER {
		asc, desc
	}

	private String query;
	private int rows;
	private TuringSortField sortField;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public TuringSortField getSortField() {
		return sortField;
	}

	public void setSortField(TuringSortField sortField) {
		this.sortField = sortField;
	}

	public void setSortField(String field, TuringQuery.ORDER sort) {
		if (this.sortField == null) {
			this.sortField = new TuringSortField();
		}
		this.sortField.setField(field);
		this.sortField.setSort(sort);
	}

}

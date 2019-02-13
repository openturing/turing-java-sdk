package com.viglet.turing.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TuringQuery {
	public enum ORDER {
		asc, desc
	}

	private String query;
	private int rows;
	private TuringSortField sortField;
	private TurClientBetweenDates betweenDates;
	private List<String> fieldQueries;
	private List<String> targetingRules;
	private int pageNumber;

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

	public void setSortField(TuringQuery.ORDER sort) {
		if (this.sortField == null) {
			this.sortField = new TuringSortField();
		}
		this.sortField.setField(null);
		this.sortField.setSort(sort);
	}

	public TurClientBetweenDates getBetweenDates() {
		return betweenDates;
	}

	public void setBetweenDates(TurClientBetweenDates betweenDates) {
		this.betweenDates = betweenDates;
	}

	public void setBetweenDates(String field, Date startDate, Date endDate) {

		this.betweenDates = new TurClientBetweenDates(field, startDate, endDate);
	}

	public void addFilterQuery(String... fq) {
		if (this.fieldQueries == null) {
			this.fieldQueries = new ArrayList<String>();
		}
		for (int i = 0; i < fq.length; i++) {
			fieldQueries.add(fq[i]);
		}
	}

	public List<String> getFieldQueries() {
		return fieldQueries;
	}

	public void setFieldQueries(List<String> fieldQueries) {
		this.fieldQueries = fieldQueries;
	}

	public void addTargetingRule(String... tr) {
		if (this.targetingRules == null) {
			this.targetingRules = new ArrayList<String>();
		}
		for (int i = 0; i < tr.length; i++) {
			targetingRules.add(tr[i]);
		}
	}

	public List<String> getTargetingRules() {
		return targetingRules;
	}

	public void setTargetingRules(List<String> targetingRules) {
		this.targetingRules = targetingRules;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

}

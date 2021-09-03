package com.viglet.turing.client.sn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Return details about Turing AI response query.
 * 
 * @since 0.3.4
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurSNSiteSearchQueryContextQuery {

	private String queryString;
	private String sort;
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

}

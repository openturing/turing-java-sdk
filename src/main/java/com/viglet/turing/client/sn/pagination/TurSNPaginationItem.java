/*
 * Copyright (C) 2016-2021 the original author or authors. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.viglet.turing.client.sn.pagination;

import com.viglet.turing.api.sn.bean.TurSNSiteSearchPaginationBean;

/**
 * Pagination of results of Turing AI Semantic Navigation response with friendly
 * attributes.
 * 
 * @since 0.3.4
 */
public class TurSNPaginationItem {

	private String type;
	private String label;
	private String apiURL;
	private int pageNumber;

	public TurSNPaginationItem(TurSNSiteSearchPaginationBean turSNSiteSearchPaginationBean) {
		super();
		this.setType(turSNSiteSearchPaginationBean.getType());
		this.setApiURL(turSNSiteSearchPaginationBean.getHref());
		this.setLabel(turSNSiteSearchPaginationBean.getText());
		this.setPageNumber(turSNSiteSearchPaginationBean.getPage());
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getApiURL() {
		return apiURL;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

}

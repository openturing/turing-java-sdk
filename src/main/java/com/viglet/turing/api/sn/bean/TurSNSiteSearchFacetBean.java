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

package com.viglet.turing.api.sn.bean;

import java.util.List;

/**
 * Facets of Turing AI Semantic Navigation response.
 * 
 * @since 0.3.4
 */
public class TurSNSiteSearchFacetBean {

	private List<TurSNSiteSearchFacetItemBean> facets;
	private TurSNSiteSearchFacetLabelBean label;
	public List<TurSNSiteSearchFacetItemBean> getFacets() {
		return facets;
	}
	public void setFacets(List<TurSNSiteSearchFacetItemBean> facets) {
		this.facets = facets;
	}
	public TurSNSiteSearchFacetLabelBean getLabel() {
		return label;
	}
	public void setLabel(TurSNSiteSearchFacetLabelBean label) {
		this.label = label;
	}

}

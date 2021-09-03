package com.viglet.turing.client.sn;

import com.viglet.turing.api.sn.bean.TurSNSiteSearchDocumentBean;

/**
 * Get Info about a document result.
 * 
 * @since 0.3.4
 */
public class TurSNDocument {

	private TurSNSiteSearchDocumentBean turSNSiteSearchDocumentBean;

	public Object getFieldValue(String field) {
		if (turSNSiteSearchDocumentBean != null && turSNSiteSearchDocumentBean.getFields() != null && turSNSiteSearchDocumentBean.getFields().containsKey(field)) {
			return turSNSiteSearchDocumentBean.getFields().get(field);
		} else {
			return null;
		}
	}

	public TurSNSiteSearchDocumentBean getContent() {
		return turSNSiteSearchDocumentBean;
	}

	public void setContent(TurSNSiteSearchDocumentBean turSNSiteSearchDocumentBean) {
		this.turSNSiteSearchDocumentBean = turSNSiteSearchDocumentBean;
	}

}

package com.viglet.turing.client.sn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.viglet.turing.api.sn.bean.TurSNSiteSearchQueryContext;

public class TurSNDocumentList implements Iterable<TurSNDocument> {
	private List<TurSNDocument> turSNDocuments = new ArrayList<>();
	private TurSNSiteSearchQueryContext queryContext;
	@Override
	public Iterator<TurSNDocument> iterator() {
		return turSNDocuments.iterator();
	}

	public List<TurSNDocument> getTurSNDocuments() {
		return turSNDocuments;
	}

	public void setTurSNDocuments(List<TurSNDocument> turSNDocuments) {
		this.turSNDocuments = turSNDocuments;
	}
	
	public TurSNSiteSearchQueryContext getQueryContext() {
		return queryContext;
	}

	public void setQueryContext(TurSNSiteSearchQueryContext queryContext) {
		this.queryContext = queryContext;
	}
	
}
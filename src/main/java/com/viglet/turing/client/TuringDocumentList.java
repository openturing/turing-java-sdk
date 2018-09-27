package com.viglet.turing.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.viglet.turing.sn.TurSNSiteSearchQueryContext;

public class TuringDocumentList implements Iterable<TuringDocument> {
	private List<TuringDocument> turingDocuments = new ArrayList<TuringDocument>();
	private TurSNSiteSearchQueryContext queryContext;
	@Override
	public Iterator<TuringDocument> iterator() {
		return turingDocuments.iterator();
	}

	public List<TuringDocument> getTuringDocuments() {
		return turingDocuments;
	}

	public void setTuringDocuments(List<TuringDocument> turingDocuments) {
		this.turingDocuments = turingDocuments;
	}
	
	public TurSNSiteSearchQueryContext getQueryContext() {
		return queryContext;
	}

	public void setQueryContext(TurSNSiteSearchQueryContext queryContext) {
		this.queryContext = queryContext;
	}
	
}
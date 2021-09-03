package com.viglet.turing.client.sn.response;

import com.viglet.turing.client.sn.TurSNDocumentList;

/**
 * Return results of Turing AI request.
 * 
 * @author Alexandre Oliveira
 * 
 * @since 0.3.4
 */
public class QueryTurSNResponse {

	private TurSNDocumentList results;

	public TurSNDocumentList getResults() {
		return results;
	}

	public void setResults(TurSNDocumentList results) {
		this.results = results;
	}
	
	
}

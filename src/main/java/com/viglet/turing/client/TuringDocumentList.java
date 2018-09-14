package com.viglet.turing.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TuringDocumentList implements Iterable<TuringDocument> {
	private List<TuringDocument> turingDocuments = new ArrayList<TuringDocument>();

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

	
}
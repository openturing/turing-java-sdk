package com.viglet.turing.client.sn.facet;

public class TurSNFacetField {

	private String label;

	private TurSNFacetFieldValueList values;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public TurSNFacetFieldValueList getValues() {
		return values;
	}

	public void setValues(TurSNFacetFieldValueList values) {
		this.values = values;
	}

	public int getValueCount() {
		return values.getTurSNFacetFieldValues().size();
	}
}

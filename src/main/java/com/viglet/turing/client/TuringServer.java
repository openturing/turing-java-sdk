package com.viglet.turing.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import com.viglet.turing.client.response.QueryTuringResponse;

public class TuringServer {
	private String turingServer;

	private TuringQuery turingQuery;

	public TuringServer(String turingServer) {
		super();
		this.turingServer = turingServer;

	}

	public String getTuringServer() {
		return turingServer;
	}

	public void setTuringServer(String turingServer) {
		this.turingServer = turingServer;
	}

	public QueryTuringResponse query(TuringQuery turingQuery) {
		this.turingQuery = turingQuery;
		CloseableHttpClient client = HttpClients.createDefault();
		QueryTuringResponse queryTuringResponse = new QueryTuringResponse();
		URIBuilder turingURL;

		HttpGet httpGet;
		try {
			turingURL = new URIBuilder(turingServer + "/search").addParameter("q", this.turingQuery.getQuery());
			if (this.turingQuery.getRows() > 0) {
				turingURL.addParameter("sort", Integer.toString(this.turingQuery.getRows()));
			}
			httpGet = new HttpGet(turingURL.build());

			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");
			httpGet.setHeader("Accept-Encoding", "UTF-8");
			HttpResponse response;

			response = client.execute(httpGet);

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			JSONArray resultJSON = new JSONObject(result.toString()).getJSONObject("results").getJSONArray("document");

			TuringDocumentList turingDocumentList = new TuringDocumentList();
			List<TuringDocument> turingDocuments = new ArrayList<TuringDocument>();

			for (int i = 0; i < resultJSON.length(); i++) {
				TuringDocument turingDocument = new TuringDocument();
				turingDocument.setContent(resultJSON.getJSONObject(i));
				turingDocuments.add(turingDocument);
			}

			turingDocumentList.setTuringDocuments(turingDocuments);

			queryTuringResponse.setResults(turingDocumentList);

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return queryTuringResponse;
	}
}

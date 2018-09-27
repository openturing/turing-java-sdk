package com.viglet.turing.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viglet.turing.client.TuringQuery.ORDER;
import com.viglet.turing.client.response.QueryTuringResponse;
import com.viglet.turing.sn.TurSNSiteSearchQueryContext;

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

			// Rows
			if (this.turingQuery.getRows() > 0) {
				turingURL.addParameter("rows", Integer.toString(this.turingQuery.getRows()));
			}

			// Field Query
			if (this.turingQuery.getFieldQueries() != null) {
				for (String fieldQuery : this.turingQuery.getFieldQueries()) {
					turingURL.addParameter("fq[]", fieldQuery);
				}
			}

			// Sort
			if (this.turingQuery.getSortField() != null) {
				TuringSortField turingSortField = this.turingQuery.getSortField();

				if (turingSortField.getSort() != null) {
					if (turingSortField.getField() == null) {
						String orderMod = null;
						if (turingSortField.getSort().name().equals(ORDER.desc.name())) {
							orderMod = "newest";
						} else if (turingSortField.getSort().name().equals(ORDER.asc.name())) {
							orderMod = "oldest";
						} else {
							orderMod = "relevance";
						}
						turingURL.addParameter("sort", orderMod);
					} else {
						turingURL.addParameter("sort",
								String.format("%s %s", turingSortField.getField(), turingSortField.getSort().name()));
					}
				}
			}

			// Between Dates
			if (this.turingQuery.getBetweenDates() != null) {
				TurClientBetweenDates turClientBetweenDates = this.turingQuery.getBetweenDates();
				if (turClientBetweenDates.getField() != null && turClientBetweenDates.getStartDate() != null
						&& turClientBetweenDates.getEndDate() != null) {
					TimeZone tz = TimeZone.getTimeZone("UTC");
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
					df.setTimeZone(tz);

					String fieldDate = turClientBetweenDates.getField();
					String startDate = df.format(turClientBetweenDates.getStartDate());
					String endDate = df.format(turClientBetweenDates.getEndDate());

					turingURL.addParameter("fq[]", String.format("%s:[%s TO %s]", fieldDate, startDate, endDate));
				}
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

			JSONObject queryContextJSON = new JSONObject(result.toString()).getJSONObject("queryContext");
			ObjectMapper mapper = new ObjectMapper();
			TurSNSiteSearchQueryContext turSNSiteSearchQueryContext = mapper.readValue(queryContextJSON.toString(),
					TurSNSiteSearchQueryContext.class);

			turingDocumentList.setTuringDocuments(turingDocuments);
			turingDocumentList.setQueryContext(turSNSiteSearchQueryContext);
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

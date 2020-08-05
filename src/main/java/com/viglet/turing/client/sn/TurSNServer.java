package com.viglet.turing.client.sn;

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
import com.viglet.turing.client.sn.TurSNQuery.ORDER;
import com.viglet.turing.client.sn.response.QueryTurSNResponse;

import java.util.logging.*;

public class TurSNServer {

	private static Logger logger = Logger.getLogger(TurSNServer.class.getName());

	private String turSNServer;

	private TurSNQuery turSNQuery;

	public TurSNServer(String turSNServer) {
		super();
		this.turSNServer = turSNServer;

	}

	public String getTuringServer() {
		return turSNServer;
	}

	public void setTuringServer(String turingServer) {
		this.turSNServer = turingServer;
	}

	public QueryTurSNResponse query(TurSNQuery turSNQuery) {
		this.turSNQuery = turSNQuery;
		CloseableHttpClient client = HttpClients.createDefault();
		QueryTurSNResponse queryTuringResponse = new QueryTurSNResponse();
		URIBuilder turingURL;

		HttpGet httpGet;
		try {
			turingURL = new URIBuilder(turSNServer + "/search").addParameter("q", this.turSNQuery.getQuery());

			// Rows
			if (this.turSNQuery.getRows() > 0) {
				turingURL.addParameter("rows", Integer.toString(this.turSNQuery.getRows()));
			}

			// Field Query
			if (this.turSNQuery.getFieldQueries() != null) {
				for (String fieldQuery : this.turSNQuery.getFieldQueries()) {
					turingURL.addParameter("fq[]", fieldQuery);
				}
			}

			// Targeting Rule
			if (this.turSNQuery.getTargetingRules() != null) {
				for (String targetingRule : this.turSNQuery.getTargetingRules()) {
					turingURL.addParameter("tr[]", targetingRule);
				}
			}

			// Sort
			if (this.turSNQuery.getSortField() != null) {
				TurSNSortField turSortField = this.turSNQuery.getSortField();

				if (turSortField.getSort() != null) {
					if (turSortField.getField() == null) {
						String orderMod = null;
						if (turSortField.getSort().name().equals(ORDER.desc.name())) {
							orderMod = "newest";
						} else if (turSortField.getSort().name().equals(ORDER.asc.name())) {
							orderMod = "oldest";
						} else {
							orderMod = "relevance";
						}
						turingURL.addParameter("sort", orderMod);
					} else {
						turingURL.addParameter("sort",
								String.format("%s %s", turSortField.getField(), turSortField.getSort().name()));
					}
				}
			}

			// Between Dates
			if (this.turSNQuery.getBetweenDates() != null) {
				TurSNClientBetweenDates turClientBetweenDates = this.turSNQuery.getBetweenDates();
				if (turClientBetweenDates.getField() != null && turClientBetweenDates.getStartDate() != null
						&& turClientBetweenDates.getEndDate() != null) {
					TimeZone tz = TimeZone.getTimeZone("UTC");
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
					df.setTimeZone(tz);

					String fieldDate = turClientBetweenDates.getField();
					String startDate = df.format(turClientBetweenDates.getStartDate());
					String endDate = df.format(turClientBetweenDates.getEndDate());

					turingURL.addParameter("fq[]", String.format("%s:[%s TO %s]", fieldDate, startDate, endDate));
				}
			}

			// Page Number
			if (this.turSNQuery.getPageNumber() > 0) {
				turingURL.addParameter("p", String.format(Integer.toString(this.turSNQuery.getPageNumber())));
			} else {
				turingURL.addParameter("p", "1");
			}

			httpGet = new HttpGet(turingURL.build());

			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");
			httpGet.setHeader("Accept-Encoding", "UTF-8");
			HttpResponse response;

			logger.info(String.format("Viglet Turing Request: %s", turingURL.build().toString()));

			response = client.execute(httpGet);

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			JSONArray resultJSON = new JSONObject(result.toString()).getJSONObject("results").getJSONArray("document");

			TurSNDocumentList turSNDocumentList = new TurSNDocumentList();
			List<TurSNDocument> turSNDocuments = new ArrayList<>();

			for (int i = 0; i < resultJSON.length(); i++) {
				TurSNDocument turSNDocument = new TurSNDocument();
				turSNDocument.setContent(resultJSON.getJSONObject(i));
				turSNDocuments.add(turSNDocument);
			}

			JSONObject queryContextJSON = new JSONObject(result.toString()).getJSONObject("queryContext");
			ObjectMapper mapper = new ObjectMapper();
			TurSNSiteSearchQueryContext turSNSiteSearchQueryContext = mapper.readValue(queryContextJSON.toString(),
					TurSNSiteSearchQueryContext.class);

			turSNDocumentList.setTurSNDocuments(turSNDocuments);
			turSNDocumentList.setQueryContext(turSNSiteSearchQueryContext);
			queryTuringResponse.setResults(turSNDocumentList);

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

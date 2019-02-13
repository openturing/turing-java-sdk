
[![](https://jitpack.io/v/openviglet/turing-java-sdk.svg)](https://jitpack.io/#openviglet/turing-java-sdk)

# Java library to access Viglet Turing


Documentation: [https://openturing.github.io/turing-java-sdk/](https://openturing.github.io/turing-java-sdk/)

```java
import com.viglet.turing.client.TuringDocumentList;
import com.viglet.turing.client.TuringQuery;
import com.viglet.turing.client.TuringServer;
import com.viglet.turing.client.response.QueryTuringResponse;

class HelloWorldApp {
	public static void main(String[] args) {

		HttpTuringServer turingServer = new HttpTuringServer("http://localhost:2700/api/sn/Sample");

		TuringQuery query = new TuringQuery();
		query.setQuery("hello");
		query.setRows(10);
		query.setSortField(TuringQuery.ORDER.asc);
		query.setPageNumber(1);

		QueryTuringResponse response = turingServer.query(query);
		TuringDocumentList turingResults = response.getResults();
	}
}	
```
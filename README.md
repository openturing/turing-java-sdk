
[![](https://jitpack.io/v/openturing/turing-java-sdk.svg)](https://jitpack.io/#openturing/turing-java-sdk)

[![viglet_logo.png](https://openturing.github.io/turing/img/banner/viglet_turing.png)](http://viglet.com/turing)

# Java library to access Viglet Turing


Documentation: [https://openturing.github.io/turing-java-sdk/](https://openturing.github.io/turing-java-sdk/)

```java
import com.viglet.turing.client.sn.TurSNDocumentList;
import com.viglet.turing.client.sn.TurSNQuery;
import com.viglet.turing.client.sn.TurSNServer;
import com.viglet.turing.client.sn.response.QueryTuringResponse;

class HelloWorldApp {
	public static void main(String[] args) {

		HttpTurSNServer turSNServer = new HttpTurSNServer("http://localhost:2700/api/sn/Sample");

		TurSNQuery query = new TurSNQuery();
		query.setQuery("hello");
		query.setRows(10);
		query.setSortField(TuringQuery.ORDER.asc);
		query.setPageNumber(1);

		QueryTurSNResponse response = turSNServer.query(query);
		TurSNDocumentList turSNResults = response.getResults();
	}
}	
```
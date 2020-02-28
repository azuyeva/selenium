package steps.api;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class RestClient {

    String url;

    public RestClient(String url) {
        this.url = url;
    }

    public HttpEntity sendGetRequest(String route) throws IOException {

        HttpUriRequest request = new HttpGet( url + route);
        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        return httpResponse.getEntity();
        //String result = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
        //System.out.println(result);
    }

}

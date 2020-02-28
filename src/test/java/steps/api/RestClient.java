package steps.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class RestClient {

    String url;

    public RestClient(String url) {
        this.url = url;
    }

    public HttpResponse sendGetRequest(String route) throws IOException {

        HttpUriRequest request = new HttpGet( url + route);
        return HttpClientBuilder.create().build().execute( request );
    }

    public HttpResponse sendPostRequest(String route, String bodyJson) throws IOException {

        HttpPost request = new HttpPost(url + route);
        request.setHeader("Content-type", "application/json; charset=UTF-8");
        request.setEntity(new StringEntity(bodyJson));
        return HttpClientBuilder.create().build().execute(request);
    }
}

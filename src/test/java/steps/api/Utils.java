package steps.api;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Utils {

    public static <T> T jsonToObject(String json, Class<T> clazz) {

        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static String objectToJson(Object object) {

        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static String httpEntityContentToString(HttpEntity response) throws IOException {
        return IOUtils.toString(response.getContent(), StandardCharsets.UTF_8);
    }
}

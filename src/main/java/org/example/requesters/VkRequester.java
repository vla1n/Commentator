package org.example.requesters;

import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.utils.JsonUtils;

import java.util.List;

public class VkRequester {

    private final String VK_API_HOST = "https://api.vk.com/method/";
    private final String VERSION = "5.131";

    private final String accessToken;

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    public VkRequester(String accessToken) {
        this.accessToken = accessToken;
    }

    public String sendGetRequest(String method, String params) {
        return executeRequest(new HttpGet(
                createUrl(method, JsonUtils.jsonToParamList(params))
        ));
    }

    public String sendPostRequest(String method, String params) {
        return executeRequest(new HttpPost(
                createUrl(method, JsonUtils.jsonToParamList(params))
        ));
    }

    @SneakyThrows
    private String executeRequest(HttpRequestBase request) {
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            String result = "";
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
            return result;
        }
    }

    @SneakyThrows
    private String createUrl(String method, List<String> params) {
        URIBuilder ub = new URIBuilder(VK_API_HOST + method);
        params.forEach(p -> {
            String[] array = p.split("=");
            ub.addParameter(array[0], array[1]);
        });
        ub.addParameter("access_token", accessToken);
        ub.addParameter("v", VERSION);

        return ub.toString();
    }
}

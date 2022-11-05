package Controllers;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ConsumidorAPI {

    private static String URLBase = "";

    private static ConsumidorAPI instance;

    private CloseableHttpClient clientHTTP;

    private ConsumidorAPI() {
        this.clientHTTP = HttpClients.createDefault();
    }

    public static ConsumidorAPI getInstance() {
        if (instance == null) {
            instance = new ConsumidorAPI();
        }
        return instance;
    }

    public String doPost(String url, String body) {
        String responseBody = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("authorization", "Bearer " + System.getProperty("accessToken"));
            StringEntity stringEntity = new StringEntity(body, "UTF-8");
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);

            ResponseHandler<String> responseHandler = (final HttpResponse response) -> {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 600) {
                    HttpEntity entity = response.getEntity();
                    String json_str = EntityUtils.toString(entity);
//                    System.out.println(json_str);
                    System.setProperty("code", String.valueOf(statusCode));
//                    return entity != null ? EntityUtils.toString(entity) : null;
                    return json_str;
                } else {
//                    throw new ClientProtocolException("Unexpected response status: " + statusCode);
                    System.setProperty("code", String.valueOf(statusCode));
                }
                return null;
            };

            responseBody = this.clientHTTP.execute(httpPost, responseHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }

    public String doPatch(String url, String body) {
        String responseBody = null;
        try {
            HttpPatch httpPatch = new HttpPatch(url);
            httpPatch.setHeader("Content-type", "application/json");
            httpPatch.setHeader("authorization", "Bearer " + System.getProperty("accessToken"));
            StringEntity stringEntity = new StringEntity(body, "UTF-8");
            httpPatch.getRequestLine();
            httpPatch.setEntity(stringEntity);

            ResponseHandler<String> responseHandler = (final HttpResponse response) -> {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 600) {
                    HttpEntity entity = response.getEntity();
                    String json_str = EntityUtils.toString(entity);
                    System.setProperty("code", String.valueOf(statusCode));
//                    return entity != null ? EntityUtils.toString(entity) : null;
                    return json_str;
                } else {
//                    throw new ClientProtocolException("Unexpected response status: " + statusCode);
                    System.setProperty("code", String.valueOf(statusCode));
                }
                return null;
            };

            responseBody = this.clientHTTP.execute(httpPatch, responseHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }

    public String doGet(String url) {
        String responseBody = null;
        try {
            HttpGet httpGet = new HttpGet(ConsumidorAPI.URLBase + url);
            httpGet.setHeader("authorization", "Bearer " + System.getProperty("accessToken"));

            ResponseHandler<String> responseHandler = (final HttpResponse response) -> {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 600) {
                    HttpEntity entity = response.getEntity();
                    String json_str = EntityUtils.toString(entity);
                    System.setProperty("code", String.valueOf(statusCode));
//                    return entity != null ? EntityUtils.toString(entity) : null;
                    return json_str;
                } else {
//                    throw new ClientProtocolException("Unexpected response status: " + statusCode);
                    System.setProperty("code", String.valueOf(statusCode));
                }
                return null;
            };
            responseBody = this.clientHTTP.execute(httpGet, responseHandler);
        } catch (IOException e) {
            System.out.println("error: " + e);
        }

        return responseBody;
    }

    public String doDelete(String url) {
        String responseBody = null;
        try {
            HttpDelete httpDelete = new HttpDelete(url);
            httpDelete.setHeader("authorization", "Bearer " + System.getProperty("accessToken"));

            ResponseHandler<String> responseHandler = (final HttpResponse response) -> {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 600) {
                    System.setProperty("code", String.valueOf(statusCode));
//                    return entity != null ? EntityUtils.toString(entity) : null;
                    return null;
                } else {
//                    throw new ClientProtocolException("Unexpected response status: " + statusCode);
                    System.setProperty("code", String.valueOf(statusCode));
                }
                return null;
            };

            responseBody = this.clientHTTP.execute(httpDelete, responseHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }
}

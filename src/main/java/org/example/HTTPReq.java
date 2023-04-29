package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HTTPReq {
    private final OkHttpClient httpClient = new OkHttpClient();
    private final String ApiKey = "API_KEY";
    private double fromPrice;
    private double toPrice;

    public double getFromPrice() {

        return fromPrice;
    }

    public double getToPrice() {
        return toPrice;
    }

    public void sendRequest(String convertFrom, String convertTo) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest").newBuilder();
        urlBuilder.addQueryParameter("symbol", convertFrom.toUpperCase() + "," + convertTo.toUpperCase());
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-CMC_PRO_API_KEY", ApiKey)
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseBody = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseBody);
        this.fromPrice = jsonNode.get("data").get(convertFrom.toUpperCase()).get("quote").get("USD").get("price").asDouble();
        this.toPrice = jsonNode.get("data").get(convertTo.toUpperCase()).get("quote").get("USD").get("price").asDouble();
    }
}

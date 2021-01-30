package com.xzw;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TestHttpClient {

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet =new HttpGet("http://localhost:8803");
        CloseableHttpResponse response  = httpClient.execute(httpGet);
        System.out.println("响应状态为:" + response.getStatusLine());
        if(response.getEntity()!=null) {
            System.out.println("响应内容长度为:" + response.getEntity().getContentLength());
            System.out.println("响应内容类型为" + response.getEntity().getContentType());
            System.out.println(EntityUtils.toString(response.getEntity()));
//            System.out.println(EntityUtils.toString(response.getEntity()));
        }

    }


}

package com.facebank.usersupport.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by sunjunchen on 2017/10/13.
 */
@Component
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(@Qualifier("httpComponentsClientHttpRequestFactory") ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public RestTemplate restRetryTemplate(@Qualifier("httpComponentsClientHttpRequestRetryFactory") ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean(name="httpComponentsClientHttpRequestFactory")
    public ClientHttpRequestFactory httpComponentsClientHttpRequestFactory(){
        ClientHttpRequestFactory factory = clientHttpRequestFactory(false);
        return factory;
    }

    @Bean(name="httpComponentsClientHttpRequestRetryFactory")
    public ClientHttpRequestFactory httpComponentsClientHttpRequestRetryFactory(){
        ClientHttpRequestFactory factory = clientHttpRequestFactory(true);
        return factory;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(boolean isRetry) {
        // 长连接保持30秒
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(30000, TimeUnit.MILLISECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(200);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(10);

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        // 重试次数，默认是3次，没有开启
        if (isRetry) {
            httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true));
        } else {
            httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        }
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE);

        HttpClient httpClient = httpClientBuilder.build();

        // httpClient连接配置，底层是配置RequestConfig
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        // 连接超时
        clientHttpRequestFactory.setConnectTimeout(5000);
        // 数据读取超时时间，即SocketTimeout
        clientHttpRequestFactory.setReadTimeout(5000);
        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(200);
        // 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
        // clientHttpRequestFactory.setBufferRequestBody(false);
        return clientHttpRequestFactory;
    }
}

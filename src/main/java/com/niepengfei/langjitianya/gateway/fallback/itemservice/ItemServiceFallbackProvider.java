package com.niepengfei.langjitianya.gateway.fallback.itemservice;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jack
 */
@Component
public class ItemServiceFallbackProvider implements FallbackProvider {

    /**
     *  只对langjitianya-itemservice做降级处理, 如果需要对所有的服务都加降级处理
     *  只需要将字符串变成“*”
     * @return
     */
    @Override
    public String getRoute() {
        return "langjitianya-itemservice";
    }

    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        return this.fallbackResponse();
    }

    @Override
    public ClientHttpResponse fallbackResponse() {

        return new ClientHttpResponse(){
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("error".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };
    }
}

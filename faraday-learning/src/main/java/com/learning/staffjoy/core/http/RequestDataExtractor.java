package com.learning.staffjoy.core.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;

import java.util.Enumeration;
import java.util.List;

import static java.util.Collections.list;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * http请求数据抽取器
 */
public class RequestDataExtractor {

    /**
     * 抽取请求URI
     * @param request
     * @return
     */
    public String extractUri(HttpServletRequest request) {
        return request.getRequestURI() + getQuery(request);
    }

    /**
     * 抽取主机名
     * @param request
     * @return
     */
    public String extractHost(HttpServletRequest request) {
        return request.getServerName();
    }

    /**
     * 抽取HTTP的Header
     * @param request
     * @return
     */
    public HttpHeaders extractHttpHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            List<String> value = list(request.getHeaders(name));
            headers.put(name, value);
        }
        return headers;
    }

    /**
     * 抽取HTTP的方法
     * @param request
     * @return
     */
    public HttpMethod extractHttpMethod(HttpServletRequest request) {
        return HttpMethod.resolve(request.getMethod());
    }

    protected String getQuery(HttpServletRequest request) {
        return request.getQueryString() == null ? EMPTY : "?" + request.getQueryString();
    }
}

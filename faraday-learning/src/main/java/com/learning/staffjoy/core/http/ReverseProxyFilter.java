package com.learning.staffjoy.core.http;

import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 反向代理过滤器
 */
public class ReverseProxyFilter extends OncePerRequestFilter {

    private static final ILogger log = SLoggerFactory.getLogger(ReverseProxyFilter.class);

    protected final RequestDataExtractor extractor;

    public ReverseProxyFilter(
//            FaradayProperties faradayProperties,
            RequestDataExtractor extractor//,
//            MappingsProvider mappingsProvider,
//            RequestForwarder requestForwarder,
//            ProxyingTraceInterceptor traceInterceptor,
//            PreForwardRequestInterceptor requestInterceptor
    ) {
        //this.faradayProperties = faradayProperties;
        this.extractor = extractor;
//        this.mappingsProvider = mappingsProvider;
//        this.requestForwarder = requestForwarder;
//        this.traceInterceptor = traceInterceptor;
//        this.preForwardRequestInterceptor = requestInterceptor;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String originUri = extractor.extractUri(request);
        String originHost = extractor.extractHost(request);

        log.debug("Incoming request", "method", request.getMethod(),
                "host", originHost,
                "uri", originUri);

        HttpHeaders headers = extractor.extractHttpHeaders(request);
        HttpMethod method = extractor.extractHttpMethod(request);

//        String traceId = traceInterceptor.generateTraceId();
//        traceInterceptor.onRequestReceived(traceId, method, originHost, originUri, headers);
//
//        MappingProperties mapping = mappingsProvider.resolveMapping(originHost, request);
//        if (mapping == null) {
//            traceInterceptor.onNoMappingFound(traceId, method, originHost, originUri, headers);
//
//            log.debug(String.format("Forwarding: %s %s %s -> no mapping found", method, originHost, originUri));
//
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().println("Unsupported domain");
//            return;
//        } else {
//            log.debug(String.format("Forwarding: %s %s %s -> %s", method, originHost, originUri, mapping.getDestinations()));
//        }
//
//        byte[] body = extractor.extractBody(request);
//        addForwardHeaders(request, headers);
//
//        RequestData dataToForward = new RequestData(method, originHost, originUri, headers, body, request);
//        preForwardRequestInterceptor.intercept(dataToForward, mapping);
//        if (dataToForward.isNeedRedirect() && !isBlank(dataToForward.getRedirectUrl())) {
//            log.debug(String.format("Redirecting to -> %s", dataToForward.getRedirectUrl()));
//            response.sendRedirect(dataToForward.getRedirectUrl());
//            return;
//        }
//
//        ResponseEntity<byte[]> responseEntity =
//                requestForwarder.forwardHttpRequest(dataToForward, traceId, mapping);
//        this.processResponse(response, responseEntity);
    }
}

package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {
    private static final Logger log = LoggerFactory.getLogger(RequestBuilder.class);

    private String url = null;
    private HttpHeaders headers = new HttpHeaders();
    private Map<String, String> params = new HashMap<>();
    private HttpMethod method = HttpMethod.GET;
    private Object body = null;
    private boolean encodeUrl = true;

    private RequestBuilder(String url) {
        this.url = url;
    }

    public static RequestBuilder prepareCall(String url) {
        return new RequestBuilder(url);
    }

    public RequestBuilder withHeader(String name, String value) {
        this.headers.add(name, value);
        return this;
    }

    public RequestBuilder withHeaders(HttpHeaders headers) {
        headers.forEach((k, v) -> this.withHeader(k, v.get(0)));
        return this;
    }

    public RequestBuilder withJsonHeaders() {
        this.headers.setContentType(MediaType.valueOf("application/json"));
        this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return this;
    }

    public RequestBuilder withMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public RequestBuilder withGetMethod() {
        return withMethod(HttpMethod.GET);
    }

    public RequestBuilder withPostMethod() {
        return withMethod(HttpMethod.POST);
    }

    public RequestBuilder withPutMethod() {
        return withMethod(HttpMethod.PUT);
    }

    public RequestBuilder withDeleteMethod() {
        return withMethod(HttpMethod.DELETE);
    }

    public RequestBuilder withQueryParam(String name, String value) {
        this.params.put(name, value);
        return this;
    }

    public RequestBuilder withQueryParams(Map<String, String> queryParams) {
        this.params.putAll(queryParams);
        return this;
    }

    public RequestBuilder withBody(Object body) {
        this.body = body;
        return this;
    }

    public RequestBuilder withEncodedUrl(boolean encodeUrl) {
        this.encodeUrl = encodeUrl;
        return this;
    }

    public <ResponseType> ResponseEntity<ResponseType> makeCall(RestOperations restTemplate) {
        return this.makeCall(restTemplate, null, null);
    }

    public <ResponseType> ResponseEntity<ResponseType> makeCall(RestOperations restTemplate, 
                                                                Class<ResponseType> type) {
        return this.makeCall(restTemplate, type, null);
    }

    public <ResponseType> ResponseEntity<ResponseType> makeCall(RestOperations restTemplate, 
                                                                ParameterizedTypeReference<ResponseType> typeReference) {
        return this.makeCall(restTemplate, null, typeReference);
    }

    private <ResponseType> ResponseEntity<ResponseType> makeCall(RestOperations template,
                                                                 Class<ResponseType> type,
                                                                 ParameterizedTypeReference<ResponseType> typeReference) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(this.url);
        if (!this.params.isEmpty()) {
            params.forEach(urlBuilder::queryParam);
        }

        try {
            URI uri = encodeUrl ? urlBuilder.build().toUri() : new URI(urlBuilder.build().toUriString());
            RequestEntity request = new RequestEntity<>(this.body, this.headers, this.method, uri);

            if(typeReference == null){
                return template.exchange(request, type);
            } else {
                return template.exchange(request, typeReference);
            }

        } catch (RestClientResponseException e) {
            log.error("ErrorResponse from {}: Status:{}, Body:{}", this.url, e.getRawStatusCode(), e.getResponseBodyAsString());
            return null;
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public RequestBuilder acceptJson() {
        this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return this;
    }

    public RequestBuilder sendUrlFormEncoded() {
        this.headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return this;
    }
}
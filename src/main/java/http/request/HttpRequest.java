package http.request;

import http.HttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);
    private HttpRequestLine httpRequestLine;
    private HttpHeader httpHeader;
    private String body;

    public HttpRequest(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line == null) return;
        this.httpRequestLine = HttpRequestUtils.readRequestLine(line);
        this.httpHeader = HttpRequestUtils.readHeaders(br);
        if(httpRequestLine.getHttpMethod().equals("POST")) this.body = HttpRequestUtils.readBody(br, httpHeader);
    }

    public String getUri() {
        return this.httpRequestLine.getHttpUri().getUri();
    }

    public boolean wantStatic() {
        return httpRequestLine.getHttpUri().isStaticUri();
    }

    public String getContentType() {
        logger.debug("Accept : {}", httpHeader.getAccept());
        return httpHeader.getAccept().split(",")[0];
    }

    public String getHttpVersion() {
        return httpRequestLine.getHttpVersion();
    }

    public boolean isPost() {
        return this.httpRequestLine.getHttpMethod().equals("POST");
    }

    public String getBody(){
        return this.body;
    }
}

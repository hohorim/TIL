package mywas;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String src; // 사용자요청 원본 uri
    public String pageName;
    public Map<String, String> params;

    public HttpRequest(String src) {
        this.src = src;
        this.params = new HashMap<>();
    }

    public String getSrc() {
        return this.src;
    }
}

package mywas;

import java.util.HashMap;
import java.util.Map;

public class Pages {
    Map<String, String> pageList;

    public Pages() {
        this.pageList = new HashMap<>();
        this.loadPages();
    }

    public void add(){
        
    }
    private void loadPages() {
        this.pageList.put("/index.html",
                "<html><head><title>INDEX ${name}</title></head><body><div>${age}</div></body></html>");
        this.pageList.put("/home.html",
                "<html><head><title>HOME ${name}</title></head><body><div>${age}</div></body></html>");
        this.pageList.put("/404.html", "<html><head><title>404</title></head><body><div>404</div></body></html>");
    }

    public String renderPage(String pageName) {
        if (!pageName.startsWith("/")) {
            pageName = "/" + pageName;
        }
        String page = this.pageList.get(pageName);
        if(page ==null) page =this.pageList.get("/404.html");
        return page;
    }

    public String renderPage(HttpRequest httpRequest) {
        String page = this.renderPage(httpRequest.pageName);
        
        for (String s : httpRequest.params.keySet()) {
            page = page.replaceAll(toKeyFormat(s), httpRequest.params.get(s));
        }
        if (httpRequest.params.size() == 0) {
            page = page.replaceAll("[$]\\{\\w*\\}", "");
        }

        return page;
    }

    private String toKeyFormat(String key) {
        return "\\$\\{" + key + "\\}";
    }
}

package mywas;

public class DefaultInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpRequest httpRequest) {
        try {
            String uri = httpRequest.getSrc();
            String[] unp = uri.split("\\?"); // /index.do?name=abc&name2=bdf

            httpRequest.pageName = unp[0];
            if (unp.length > 1) {

                String[] params = unp[1].split("\\&");
                for (String param : params) {
                    String key = param.split("\\=")[0];
                    String value = param.split("\\=")[1];
                    httpRequest.params.put(key, value);
                }

            }
            return true;
        } catch (Exception e) {
            httpRequest.pageName = "404.html";
            return false;
        }
    }

    @Override
    public void postHandle(HttpRequest httpRequest) {

    }

    @Override
    public void afterCompletion(HttpRequest httpRequest) {

    }
}

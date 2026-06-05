package mywas;

public class ResourceRenameInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpRequest httpRequest) {
        // TODO www.do.abv.com/index.do? ~~ 이런식으로 들어올 수 있으니 정규식으로 해결
        boolean rs= false;
        httpRequest.pageName = httpRequest.pageName.replace(".do", ".html");
        return rs;
    }

    @Override
    public void postHandle(HttpRequest httpRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postHandle'");
    }

    @Override
    public void afterCompletion(HttpRequest httpRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afterCompletion'");
    }
}


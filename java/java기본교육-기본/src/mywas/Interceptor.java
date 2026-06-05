package mywas;

import java.util.ArrayList;
import java.util.List;

public class Interceptor implements HandlerInterceptor {
    List<HandlerInterceptor> interceptorList;

    public Interceptor(){
        this.interceptorList = new ArrayList<>();
        this.interceptorList.add(new DefaultInterceptor());
    }
    
    public void add(HandlerInterceptor handlerInterceptor){
        this.interceptorList.add(handlerInterceptor);
    }

    @Override
    public boolean preHandle(HttpRequest httpRequest) {
        boolean result = true;

        for (HandlerInterceptor interceptor : this.interceptorList) {
            if (!interceptor.preHandle(httpRequest)) {
                result = false;
                break;
            }
        }

        return result;
    }

    @Override
    public void postHandle(HttpRequest httpRequest) {
        for (HandlerInterceptor interceptor : this.interceptorList) {
            interceptor.postHandle(httpRequest);
        }
    }

    @Override
    public void afterCompletion(HttpRequest httpRequest) {
        for (HandlerInterceptor interceptor : this.interceptorList) {
            interceptor.afterCompletion(httpRequest);
        }
    }
    
}

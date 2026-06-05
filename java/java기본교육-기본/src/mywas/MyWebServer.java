package mywas;

// was 만들기
// 1. get 요청 받기
// 2. url 파싱
// 3. 해당 url과 파라미터 맞는 웹페이지 찾기
// 4. 응답반환
public class MyWebServer {

    public String request(String uri) {

        String page;
        Pages pages = new Pages();
        // 상속과 다형성은 딱히 관계가..
        // 상속으로 풀 문제가 아니구만

        // 서로 기능은 필요로하지만 순서가 필요함.
        // extends 상속은 의존성이 강함..
        // 이 소스에서는 의존성없이 독립적으로 개발해야함.
        
        Interceptor interceptor = new Interceptor();
        Config config = new Config();
        config.loadInterceptors(interceptor);
        
        HttpRequest httpRequest = new HttpRequest(uri);

        if (interceptor.preHandle(httpRequest)) {
            
            page = pages.renderPage(httpRequest);

        } else {
            page = pages.renderPage("404.html");

        }
        interceptor.postHandle(httpRequest);

        interceptor.afterCompletion(httpRequest);
        return page;
    }
}

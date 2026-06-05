package mywas;

public class Config {
    public void loadInterceptors(Interceptor interceptor) {
        interceptor.add(new ResourceRenameInterceptor());
    }
}

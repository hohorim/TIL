// package com.inje.conegateway.global.config;


// import java.time.Duration;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.client.reactive.ReactorClientHttpConnector;
// import org.springframework.web.reactive.function.client.WebClient;
// import org.springframework.web.util.DefaultUriBuilderFactory;

// import io.netty.channel.ChannelOption;
// import reactor.netty.http.client.HttpClient;
// import reactor.netty.resources.ConnectionProvider;

// @Configuration
// public class WebClientConfig {
    
//     @Bean
//     public DefaultUriBuilderFactory builderFactory() {
//         DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
//         factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
//         return factory;
//     }

//     @Bean
//     public HttpClient httpClient(){
//         HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20_1000);
//         return httpClient;
//     }

//     @Bean
//     public WebClient webClient(){
//         return WebClient.builder()
//             .uriBuilderFactory(builderFactory())
//             .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)) // default 256KB 
//             .clientConnector(new ReactorClientHttpConnector(httpClient()))
//             .defaultHeaders(headers -> {
//                 headers.remove("Transfer-Encoding");
//                 headers.remove("chunked");
//             })
//             .build();
//     }

//     @Bean
//     public ConnectionProvider connectionProvider() {
//         return ConnectionProvider.builder("http-pool")
//             .maxConnections(100)                 // connection pool의 갯수
//             .pendingAcquireTimeout(Duration.ofMillis(0)) //커넥션 풀에서 커넥션을 얻기 위해 기다리는 최대 시간
//             .pendingAcquireMaxCount(-1)                         //커넥션 풀에서 커넥션을 가져오는 시도 횟수 (-1: no limit)
//             .maxIdleTime(Duration.ofMillis(1000L))       //커넥션 풀에서 idle 상태의 커넥션을 유지하는 시간
//             .build();
//     }
// }

// package com.inje.conegateway.api.router;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.io.OutputStream;
// import java.io.UnsupportedEncodingException;
// import java.net.URI;
// import java.net.URLEncoder;
// import java.util.Enumeration;
// import java.util.LinkedHashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Map.Entry;
// import java.util.Optional;
// import java.util.regex.Pattern;

// import org.apache.commons.lang3.StringUtils;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.core.io.ClassPathResource;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.HttpStatusCode;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.http.client.MultipartBodyBuilder;
// import org.springframework.util.MultiValueMap;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.multipart.MultipartHttpServletRequest;
// import org.springframework.web.reactive.function.BodyInserters;
// import org.springframework.web.reactive.function.client.WebClient;
// import org.springframework.web.util.UriComponentsBuilder;
// import org.yaml.snakeyaml.Yaml;
// import org.yaml.snakeyaml.util.UriEncoder;

// import com.inje.conegateway.domain.func.service.IFuncService;
// import com.inje.conegateway.domain.token.service.IRefreshTokenService;
// import com.inje.conegateway.global.exception.ServiceErrorCode;
// import com.inje.conegateway.global.exception.ServiceRuleException;
// import com.inje.conegateway.global.handler.JwtHandler;
// import com.inje.conegateway.global.keyword.BodyKey;
// import com.inje.conegateway.global.keyword.HeaderKey;
// import com.inje.conegateway.global.keyword.JWTKeys;
// import com.inje.conegateway.global.keyword.SessionTypeKey;
// import com.inje.conegateway.global.utils.ApplicationInfo;
// import com.inje.conegateway.global.utils.TargetRoutes;

// import io.jsonwebtoken.ExpiredJwtException;
// import jakarta.annotation.PostConstruct;
// import jakarta.servlet.http.Cookie;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.extern.log4j.Log4j2;
// import reactor.core.publisher.Mono;

// @Log4j2
// @RestController
// public class GatewayController_webclient {

//     @Value("${prefix.path}")
//     private String PREFIX_PATH;

// 	@Autowired
// 	private WebClient webClient;

// 	@Autowired
// 	private IRefreshTokenService tokenService;

//     @Autowired 
//     private IFuncService funcService;

//     @SuppressWarnings("unchecked")
//     @PostConstruct
//     void init() throws Exception {
//         funcService.init();

//         Map<String, Map<String, String>> routes = null;
//         ClassPathResource classPathResource = new ClassPathResource("yaml/routes.yaml");

//         try {
//             Yaml yaml = new Yaml();
//             Map<String, Object> maps = yaml.load(classPathResource.getInputStream());
//             routes = (Map<String, Map<String, String>>) maps.get("routes");

//             for (String key : routes.keySet()) {
//                 Map<String,String> val = routes.get(key);
//                 TargetRoutes.uries.put(key, val.get("uri"));
//                 TargetRoutes.paths.put(PREFIX_PATH + val.get("path"), key);
//             }

//         } catch (IOException e) {
//             log.catching(e);
//         }
//     }

//     @RequestMapping(value = "/cone-private/**")
// 	public Object filter(
//         HttpServletRequest request, 
//         HttpServletResponse response, HttpMethod method, 
//         @RequestBody(required = false) String body, 
//         @RequestParam MultiValueMap<String, String> params) 
//     throws Exception {
    
//         HttpHeaders httpHeaders = new HttpHeaders();
//         Enumeration<String> headerNames = request.getHeaderNames();
//         while (headerNames.hasMoreElements()) {
//             String headerName = headerNames.nextElement();
//             String headerValue = request.getHeader(headerName);

//             httpHeaders.add(headerName, headerValue);
//         }

//         String token = getToken(request);
//         Map<String, Object> payload = JwtHandler.getPayload(token);
//         if(!verifyFuncRole(payload, request)) throw new ServiceRuleException(ServiceErrorCode.DENYURL);
        
//         if(token != null && payload != null){
//             String refreshToken = checkValidToken(token, request);
            

//             if (refreshToken != null) {
//                 String accessToken = JwtHandler.createToken(payload);
//                 httpHeaders.set(HeaderKey.AUTHORIZE, accessToken);
//                 httpHeaders.set(HeaderKey.REFRESH, "Y");
//                 response.setHeader(HeaderKey.AUTHORIZE, accessToken);
//                 response.setHeader(HeaderKey.REFRESH, refreshToken);
//             }
//         }

//         String path = request.getRequestURI();
// 		URI uri = routing(path);

//         if (request.getContentType() != null && request.getContentType().toLowerCase().contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {

// 			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

//             MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();

// 			for (String key : multipartRequest.getFileMap().keySet()) {
// 				List<MultipartFile> files = multipartRequest.getFiles(key);
// 				for (MultipartFile file : files) {
//                     multipartBodyBuilder.part(key, file.getResource());
// 				}
// 			}

//             return request(method, uri, path, params, httpHeaders, multipartBodyBuilder);
//         }

//         return request(method ,uri, path, params, httpHeaders, body, response);
// 	}

//     private ResponseEntity<Object> request(HttpMethod method, URI uri, String path, MultiValueMap<String, String> params, HttpHeaders headers, Object body, HttpServletResponse response){
//         return webClient
//             .method(method)
//             .uri(urlBuilder -> urlBuilder
//                 .scheme(uri.getScheme())
//                 .host(uri.getHost())
//                 .port(uri.getPort())
//                 .path(UriEncoder.decode(path))
//                 .queryParams(params)
//                 .build()
//             )
//             .headers(httpHeaders -> httpHeaders.addAll(headers))
//             .bodyValue(body==null?BodyInserters.empty():body)
//             .retrieve()
//             .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(String.class).flatMap(f-> Mono.error(new Exception(f))))
//             .toEntity(Object.class)
//             .doOnSuccess(t -> {
//                 if (t.getHeaders().getFirst(HeaderKey.FILEDOWNLOAD) != null && t.getHeaders().getFirst(HeaderKey.FILEDOWNLOAD).equals("Y")) {
//                     try {
//                         fileDownload(t, response);
//                     } catch (Exception e) {
//                         log.catching(e);
//                     }
//                 } 
//                 if(t.getHeaders().getFirst(HeaderKey.FUNCRELOAD) != null && t.getHeaders().getFirst(HeaderKey.FUNCRELOAD).equals("Y")){
//                     try {
//                         funcService.init();
//                     } catch (Exception e) {
//                         log.catching(e);
//                     }
//                 }
//             })
//             .block();
//     }
	
//     private ResponseEntity<Object> request(HttpMethod method, URI uri, String path, MultiValueMap<String, String> params, HttpHeaders headers, MultipartBodyBuilder body) {
//         return webClient
//             .method(method)            
//             .uri(urlBuilder -> urlBuilder
//                 .scheme(uri.getScheme())
//                 .host(uri.getHost())
//                 .port(uri.getPort())
//                 .path(path)
//                 .queryParams(params)
//                 .build()
//             )
//             .contentType(MediaType.MULTIPART_FORM_DATA)
//             .headers(httpHeaders -> httpHeaders.addAll(headers))
//             .body(BodyInserters.fromMultipartData(body.build()))
//             .retrieve()
//             .onStatus(HttpStatusCode::isError, res -> {
//                 return res.bodyToMono(String.class).flatMap(f -> Mono.error(new Exception(f)));
//             })
//             .toEntity(Object.class)
//             .block();
//     }
	
//     private boolean verifyFuncRole(Map<String, Object> payload, HttpServletRequest request) throws Exception{
//         boolean rs = false;

//         String path = request.getRequestURI();

//         String tokenRoles = null;
//         if(payload!=null) tokenRoles = (String) payload.get(JWTKeys.ROLES);
        
//         tokenRoles = Optional.ofNullable(tokenRoles).orElse("");
//         String[] tokenRolesArr = tokenRoles.split(",");

//         List<String> roles = ApplicationInfo.getInstance().getFuncRoles(path);
//         if(roles!=null){
//             for(String tokenRole: tokenRolesArr){
//                 if(roles.contains(tokenRole)){
//                     rs = true;
//                     break;
//                 }
//             }
//         }else{
//             rs = true;
//         }

//         return rs;
//     }

// 	private String getToken(HttpServletRequest request) throws Exception {
// 		String rs = null;

// 		final String tenant = request.getHeader(HeaderKey.TENANT);
// 		final String jwt = request.getHeader(HeaderKey.AUTHORIZE);
// 		if (StringUtils.isAnyEmpty(tenant, jwt)) {
// 			return null;
// 		}
// 		rs = jwt.replaceAll("Bearer ", "");

// 		return rs.length() < 32 ? null : rs;
// 	}

// 	private String checkValidToken(String accessToken, HttpServletRequest request) throws Exception {
// 		String refreshToken=null;
// 		Map<String, Object> payload = JwtHandler.getPayload(accessToken);
// 		String userId = (String) payload.get(JWTKeys.USERID);
// 		String tenantId = (String) payload.get(JWTKeys.TENANTID);

// 		try {
// 			JwtHandler.verifiyToken(accessToken);
// 		} catch (ExpiredJwtException ex) {
// 			String headerRefreshToken=request.getHeader(HeaderKey.REFRESH);

// 			if(headerRefreshToken==null){
// 				throw new ServiceRuleException(ServiceErrorCode.TIMEOVER);
// 			}else{
// 				try{
// 					String accessKey=getAccessKey(request);
// 					if(accessKey==null) throw new ServiceRuleException(ServiceErrorCode.UNVALID);
                    
// 					JwtHandler.verifiyToken(headerRefreshToken);

// 					refreshToken=tokenService.verifyByRefreshToken(tenantId, userId, accessKey, headerRefreshToken);
// 					if(refreshToken==null) throw new ServiceRuleException(ServiceErrorCode.UNVALID);

// 				}catch(ExpiredJwtException refreshEx){
// 					throw new ServiceRuleException(ServiceErrorCode.UNVALID);

// 				}catch(Exception refreshEx){
// 					throw refreshEx;
// 				}
// 			}
// 		} catch (Exception ex) {
// 			throw ex;
// 		}
// 		return refreshToken;
// 	}

// 	private String getAccessKey(HttpServletRequest request){
//         String rs =  null;

// 		List<String> sessionTypeKey=List.of(SessionTypeKey.USER,SessionTypeKey.ADMIN,SessionTypeKey.EXTERNAL);
//         Cookie[] cookies = request.getCookies();
//         if (cookies != null) {
//             for (Cookie cookie : cookies) {
// 				if(sessionTypeKey.contains(cookie.getName())){
// 					rs=cookie.getValue();
// 				}
//             }
//         }
        
//         if(rs==null) rs= request.getHeader(HeaderKey.ACCESS);

//         return rs;
// 	}

//     private URI routing(String path) throws UnsupportedEncodingException {

//         String uri = null;

//         for(Entry<String, String> targetPath: TargetRoutes.paths.entrySet()){
            
//             Pattern pattern = Pattern.compile(String.format("^%s[\\S/]*$", targetPath.getKey()));
//             if(pattern.matcher(path).matches()){
//                 uri = TargetRoutes.uries.get(targetPath.getValue());
//                 break;
//             }
//         }
        
//         return UriComponentsBuilder.fromUriString(uri).build().toUri();
//     }

// 	@SuppressWarnings("unchecked")
// 	private void fileDownload(ResponseEntity<Object> result, HttpServletResponse response) throws Exception{

// 		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) result.getBody();
// 		LinkedHashMap<String, Object> resultData = (LinkedHashMap<String, Object>) map.get(BodyKey.RESULTDATA);
		
// 		if(resultData != null){
// 			String fileName=resultData.get(BodyKey.FILENAME).toString();
// 			File file = new File(resultData.get(BodyKey.FILEPATH).toString());
// 			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+URLEncoder.encode(fileName, "UTF-8")+"\"");
// 			response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
// 			response.setHeader(HttpHeaders.CONTENT_LENGTH, ""+file.length());
// 			response.setHeader(HttpHeaders.PRAGMA, "no-cache;");
// 			response.setHeader(HttpHeaders.EXPIRES, "-1;");
			
// 			FileInputStream fileInputStream=null;
// 			OutputStream outputStream=null;
// 			try{
// 				fileInputStream = new FileInputStream(file);
// 				outputStream = response.getOutputStream();
	
// 				int read = 0;
// 				byte[] b=new byte[1024*10];
// 				while ((read = fileInputStream.read(b)) != -1) {
// 					outputStream.write(b,0,read);
// 				}
	
// 			}catch(FileNotFoundException ex){
// 				log.catching(ex);
// 			}catch(IOException ex){
// 				log.catching(ex);
// 			}catch(Exception ex){
// 				log.catching(ex);
// 			}finally{
// 				if(fileInputStream != null) {
// 					try{
// 						outputStream.flush();
// 						outputStream.close();
// 						fileInputStream.close();
						
// 					}catch(Exception ex){
// 						log.catching(ex);
// 					}
// 				}
// 			}
// 		}
// 	}
// }

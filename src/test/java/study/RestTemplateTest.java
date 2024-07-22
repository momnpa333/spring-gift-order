//package study;
//
//import static io.jsonwebtoken.Header.CONTENT_TYPE;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
//
//import java.net.URI;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.RequestEntity;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//public class RestTemplateTest {
//
//    private final RestTemplate client = new RestTemplateBuilder().build();
//
//    @Test
//    void test1() {
//        var url = "https://kauth.kakao.com/oauth/token";
//        var headers = new HttpHeaders();
//        headers.add(CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE);
//        var body = new LinkedMultiValueMap<String, String>();
//        body.add("grant_type", "authorization_code");
//        body.add("client_id", "f1af4bbe948114fcd10611ccaff2928a");
//        body.add("redirect_uri", "http://localhost:8080");
//        body.add("code",
//            "rwM9zerUjpSimGOFOOl3uqSPfv6DkeMyhxA8SFhEWaRxVMP2jSQIyQAAAAQKPCQfAAABkNhr0RPDukuslKNZWg");
//        var request = new RequestEntity<>(body, headers, HttpMethod.POST, URI.create(url));
//        var response = client.exchange(request, String.class);
//
////        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        System.out.println(response.getBody());
//    }
//}

//package study;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//import jakarta.validation.constraints.NotNull;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.web.client.RestClient;
//
////@SpringBootTest
//public class RestClientTest {
//
//    private final RestClient client = RestClient.builder().build();
//
////    @Autowired
////    private KakaoProperties properties;
//
//    @Test
//    void test1() {
//        var url = "https://kauth.kakao.com/oauth/token";
//
//        var body = createBody();
//
//        var response = client.post()
//            .uri(url)
//            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//            .body(body)
//            .retrieve()
//            .toEntity(String.class);
//
////        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        System.out.println(response);
//    }
//
//    @ConfigurationPropertiesScan
//    @ConfigurationProperties(prefix = "kakao")
//    private record KakaoProperties(
//        String client_id,
//        String redirect_uri
//    ) {
//
//    }
//
//    private static @NotNull LinkedMultiValueMap<String, String> createBody() {
//
//        var body = new LinkedMultiValueMap<String, String>();
//        body.add("grant_type", "authorization_code");
//        body.add("client_id", "f1af4bbe948114fcd10611ccaff2928a");
//        body.add("redirect_uri", "http://localhost:8080");
//        body.add("code",
//            "KjX3-8YDmyB_h-UdwEY3t6aZro0kcQA1l84tj-MI_njnyKW5LMWM9QAAAAQKPXRoAAABkNg8CP_UNEQ5evY1pg");
//        return body;
//    }
//
//}

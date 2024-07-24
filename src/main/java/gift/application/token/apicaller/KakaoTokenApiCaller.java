package gift.application.token.apicaller;

import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoTokenApiCaller {

    private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private static final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String AUTHORIZATION_GRANT_TYPE = "authorization_code";
    private static final String REFRESH_GRANT_TYPE = "refresh_token";
    private static final Duration TIMEOUT = Duration.ofSeconds(2);

    @Value("${kakao.client_id}")
    private String CLIENT_ID;
    @Value("${kakao.redirect_uri}")
    private String REDIRECT_URI;

    private final RestTemplate restTemplate;

    public KakaoTokenApiCaller(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
            .setConnectTimeout(TIMEOUT)
            .setReadTimeout(TIMEOUT)
            .build();
    }

    /**
     * refresh token을 사용해서 access token을 갱신
     */
    public String refreshAccessToken(String refreshToken) {
        var headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        var body = createUpdateAccessTokenBody(refreshToken);
        var request = new RequestEntity<>(body, headers, HttpMethod.POST,
            URI.create(KAKAO_TOKEN_URL));
        try {
            ResponseEntity<JsonNode> response = restTemplate.exchange(request, JsonNode.class);
            return response.getBody().get("access_token").asText();
        } catch (Exception e) {
            throw new RuntimeException("토큰 갱신에 실패했습니다.", e);
        }
    }

    private LinkedMultiValueMap<String, String> createUpdateAccessTokenBody(
        String refreshToken) {
        var body = new LinkedMultiValueMap<String, String>();

        body.add("grant_type", AUTHORIZATION_GRANT_TYPE);
        body.add("client_id", CLIENT_ID);
        body.add("refresh_token", refreshToken);
        return body;
    }

}

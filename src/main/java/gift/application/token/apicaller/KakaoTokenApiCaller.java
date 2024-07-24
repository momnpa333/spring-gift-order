package gift.application.token.apicaller;

import static io.jsonwebtoken.Header.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import com.fasterxml.jackson.databind.JsonNode;
import gift.application.token.dto.TokenSet;
import gift.global.validate.TimeOutException;
import java.net.URI;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoTokenApiCaller {

    private static final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String GRANT_TYPE = "authorization_code";
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
     * 인가 코드를 사용해서 토큰을 가져옴
     */
    public TokenSet getTokens(String authorizationCode) {
        var headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE);
        var body = createGetAccessTokenBody(authorizationCode);
        System.out.println(body);
        var request = new RequestEntity<>(body, headers, HttpMethod.POST,
            URI.create(KAKAO_TOKEN_URL));
        try {
            ResponseEntity<JsonNode> response = restTemplate.exchange(request, JsonNode.class);
            String accessToken = response.getBody().get("access_token").asText();
            String refreshToken = response.getBody().get("refresh_token").asText();
            return new TokenSet(accessToken, refreshToken);
        } catch (ResourceAccessException e) {
            throw new TimeOutException("네트워크 연결이 불안정 합니다.", e);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("카카오 인가 코드가 유효하지 않습니다.", e);
        }
    }

    /**
     * refresh token을 사용해서 access token을 갱신
     */
    public TokenSet refreshAccessToken(String refreshToken) {
        var headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        var body = createUpdateAccessTokenBody(refreshToken);
        var request = new RequestEntity<>(body, headers, HttpMethod.POST,
            URI.create(KAKAO_TOKEN_URL));
        try {
            ResponseEntity<JsonNode> response = restTemplate.exchange(request, JsonNode.class);
            String newAccessToken = response.getBody().get("access_token").asText();
            String newRefreshToken = response.getBody().get("refresh_token").asText();
            return new TokenSet(newAccessToken, newRefreshToken);
        } catch (Exception e) {
            throw new RuntimeException("토큰 갱신에 실패했습니다.", e);
        }
    }

    private LinkedMultiValueMap<String, String> createUpdateAccessTokenBody(
        String refreshToken) {
        var body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", REFRESH_GRANT_TYPE);
        body.add("client_id", CLIENT_ID);
        body.add("refresh_token", refreshToken);
        return body;
    }

    private LinkedMultiValueMap<String, String> createGetAccessTokenBody(
        String authorizationCode) {
        var body = new LinkedMultiValueMap<String, String>();

        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", CLIENT_ID);
        body.add("redirect_uri", REDIRECT_URI);
        body.add("code", authorizationCode);
        return body;
    }

}

package gift.application.product.service.apiCaller;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class KakaoProductApiCaller {

    private static final String KAKAO_SEND_MESSAGE_URL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
    private static final Duration TIMEOUT = Duration.ofSeconds(2);

    private final RestTemplate restTemplate;

    public KakaoProductApiCaller(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
            .setConnectTimeout(TIMEOUT)
            .setReadTimeout(TIMEOUT)
            .build();
    }

    public void sendMessage(String accessToken, String message) {
        // send message
    }

}

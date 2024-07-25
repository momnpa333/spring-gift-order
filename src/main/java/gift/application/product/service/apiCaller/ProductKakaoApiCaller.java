package gift.application.product.service.apiCaller;

import static io.jsonwebtoken.Header.CONTENT_TYPE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gift.application.product.service.apiCaller.dto.Link;
import gift.application.product.service.apiCaller.dto.TemplateObject;
import gift.global.config.KakaoProperties;
import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductKakaoApiCaller {

    private final KakaoProperties kakaoProperties;
    private final RestTemplate restTemplate;

    public ProductKakaoApiCaller(RestTemplate restTemplate,
        KakaoProperties kakaoProperties) {
        this.restTemplate = restTemplate;
        this.kakaoProperties = kakaoProperties;
    }

    public void sendMessage(String accessToken, String optionName) {
        System.out.println("sendMessage");
        System.out.println("url: " + kakaoProperties.messageRequestUri());
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();

        headers.set("Authorization", "Bearer " + accessToken);
        headers.add(CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        System.out.println("headers: " + headers);

        TemplateObject templateObject = new TemplateObject("text", makePurchaseMessage(optionName),
            new Link(null, null));

        String templateObjectJson;
        try {
            templateObjectJson = objectMapper.writeValueAsString(templateObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("메시지 전송에 실패했습니다.", e);
        }

        // HTTP 요청 데이터 설정
        var body = new LinkedMultiValueMap<>();
        body.set("template_object", templateObjectJson);
        var request = new RequestEntity<>(body, headers, HttpMethod.POST,
            URI.create(kakaoProperties.messageRequestUri()));

        try {
            ResponseEntity<JsonNode> response = restTemplate.exchange(request, JsonNode.class);
            System.out.println("response: " + response);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("메시지 전송에 실패했습니다.");
            }
        } catch (Exception e) {
            throw new RuntimeException("메시지 전송에 실패했습니다.", e);
        }


    }

    private String makePurchaseMessage(String optionName) {
        return optionName + " 상품이 구매되었습니다.";
    }
}



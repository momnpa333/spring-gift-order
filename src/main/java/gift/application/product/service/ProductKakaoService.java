package gift.application.product.service;

import gift.application.product.service.apiCaller.ProductKakaoApiCaller;
import gift.application.token.TokenManager;
import org.springframework.stereotype.Service;

@Service
public class ProductKakaoService {

    private final ProductKakaoApiCaller productKakaoApiCaller;
    private final TokenManager tokenManager;

    public ProductKakaoService(ProductKakaoApiCaller productKakaoApiCaller,
        TokenManager tokenManager) {
        this.productKakaoApiCaller = productKakaoApiCaller;
        this.tokenManager = tokenManager;
    }

    public void sendPurchaseMessage(Long memberId, String optionName) {
        String accessToken = tokenManager.getAccessToken(memberId);
        productKakaoApiCaller.sendMessage(accessToken, optionName);
    }

}

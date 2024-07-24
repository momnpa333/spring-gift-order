package gift.application.token;

import gift.application.token.apicaller.KakaoTokenApiCaller;
import gift.repository.token.TokenRepository;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

    private final KakaoTokenApiCaller kakaoTokenApiCaller;
    private final TokenRepository tokenRepository;

    public TokenProvider(KakaoTokenApiCaller kakaoTokenApiCaller, TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
        this.kakaoTokenApiCaller = kakaoTokenApiCaller;
    }

    public void saveAccessToken(Long userId, String accessToken) {
        tokenRepository.saveAccessToken(userId, accessToken);
    }

    public void saveRefreshToken(Long userId, String refreshToken) {
        tokenRepository.saveRefreshToken(userId, refreshToken);
    }

    public String getAccessToken(Long userId) {
        return tokenRepository.getAccessToken(userId);
    }

    public String getRefreshToken(Long userId) {
        return tokenRepository.getRefreshToken(userId);
    }

    public String updateAccessToken(Long userId) {
        String refreshToken = getRefreshToken(userId);
        String newAccessToken = kakaoTokenApiCaller.refreshAccessToken(refreshToken);
        saveAccessToken(userId, newAccessToken);
        return newAccessToken;
    }
}

package gift.application.token;

import gift.application.token.apicaller.KakaoTokenApiCaller;
import gift.application.token.dto.TokenSet;
import gift.repository.token.TokenRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {

    private final KakaoTokenApiCaller kakaoTokenApiCaller;
    private final TokenRepository tokenRepository;

    public TokenManager(KakaoTokenApiCaller kakaoTokenApiCaller, TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
        this.kakaoTokenApiCaller = kakaoTokenApiCaller;
    }

    /**
     * 인가 코드를 사용해서 토큰 가져오기
     */
    public TokenSet getTokens(String authorizationCode) {
        return kakaoTokenApiCaller.getTokens(authorizationCode);
    }

    public void saveTokens(Long userId, TokenSet tokenSet) {
        tokenRepository.saveAccessToken(userId, tokenSet.accessToken());
        tokenRepository.saveRefreshToken(userId, tokenSet.refreshToken());
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
        TokenSet tokenSet = kakaoTokenApiCaller.refreshAccessToken(refreshToken);
        tokenRepository.saveAccessToken(userId, tokenSet.accessToken());
        tokenRepository.saveRefreshToken(userId, tokenSet.refreshToken());
        return tokenSet.accessToken();
    }
}

package gift.model.token;

import java.time.LocalDateTime;

public class KakaoToken implements Token {

    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private Long expiresIn;
    private LocalDateTime CreatedAt;
    private LocalDateTime ExpiredAt;

    public KakaoToken() {
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public LocalDateTime getExpiredAt() {
        return ExpiredAt;
    }

    @Override
    public boolean isValid() {
        return ExpiredAt.isAfter(LocalDateTime.now());
    }

}

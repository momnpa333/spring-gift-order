package gift.repository.token;

public interface TokenRepository {

    public void saveAccessToken(Long userId, String accessToken);

    public void saveRefreshToken(Long userId, String refreshToken);

    public String getAccessToken(Long userId);

    public String getRefreshToken(Long userId);
}

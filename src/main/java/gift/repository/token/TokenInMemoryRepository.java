package gift.repository.token;

import gift.model.product.Product;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class TokenInMemoryRepository implements TokenRepository {

    private final Map<Long, String> AccessTokenStorage = new ConcurrentHashMap<>();
    private final Map<Long, String> RefreshTokenStorage = new ConcurrentHashMap<>();

    @Override
    public void saveAccessToken(Long userId, String accessToken) {
        AccessTokenStorage.put(userId, accessToken);
    }

    @Override
    public void saveRefreshToken(Long userId, String refreshToken) {
        RefreshTokenStorage.put(userId, refreshToken);
    }

    @Override
    public String getAccessToken(Long userId) {
        return AccessTokenStorage.get(userId);
    }

    @Override
    public String getRefreshToken(Long userId) {
        return RefreshTokenStorage.get(userId);
    }


}

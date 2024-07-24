package gift.repository.token;

import gift.model.product.Product;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TokenInMemoryRepository implements TokenRepository {

    private final Map<Long, String> AccessTokenStorage = new ConcurrentHashMap<>();
    private final Map<Long, String> RefreshTokenStorage = new ConcurrentHashMap<>();

    @Override
    @Transactional
    public void saveAccessToken(Long userId, String accessToken) {
        AccessTokenStorage.put(userId, accessToken);
    }

    @Override
    @Transactional
    public void saveRefreshToken(Long userId, String refreshToken) {
        RefreshTokenStorage.put(userId, refreshToken);
    }

    @Override
    @Transactional(readOnly = true)
    public String getAccessToken(Long userId) {
        return AccessTokenStorage.get(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public String getRefreshToken(Long userId) {
        return RefreshTokenStorage.get(userId);
    }


}

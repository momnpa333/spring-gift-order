package gift.repository.token;

import gift.model.token.KakaoToken;
import gift.model.token.Token;

public interface TokenRepository {

    public void saveToken(Long userId, KakaoToken token);

    public KakaoToken getToken(Long userId);
}

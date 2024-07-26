package gift.model.token;

import java.time.LocalDateTime;

public interface Token {

    String getAccessToken();

    String getRefreshToken();

    LocalDateTime getExpiredAt();

    boolean isValid();

}

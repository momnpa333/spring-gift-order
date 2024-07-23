package gift.application.member.service;

import com.fasterxml.jackson.databind.JsonNode;
import gift.application.member.dto.OAuthCommand;
import gift.application.member.service.apicaller.KaKaoApiCaller;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    private final KaKaoApiCaller kaKaoApiCaller;

    public OAuthService(KaKaoApiCaller kaKaoApiCaller) {
        this.kaKaoApiCaller = kaKaoApiCaller;
    }

    /**
     * 인가 코드를 사용해서 토큰 가져오기
     */
    private String getAccessToken(OAuthCommand.Login command) {
        return kaKaoApiCaller.getAccessToken(command.authorizationCode());
    }

    /**
     * 토큰을 사용해서 사용자 정보를 가져오는 로직
     */
    public OAuthCommand.MemberInfo getMemberInfo(OAuthCommand.Login command) {
        String accessToken = getAccessToken(command);
        JsonNode userInfo = kaKaoApiCaller.getMemberInfo(accessToken);
        return OAuthCommand.MemberInfo.from(userInfo);
    }
}

package gift.application.member.service;

import com.fasterxml.jackson.databind.JsonNode;
import gift.application.member.dto.OAuthCommand;
import gift.application.member.service.apicaller.KakaoMemberApiCaller;
import gift.application.token.TokenManager;
import org.springframework.stereotype.Service;

@Service
public class MemberOAuthService {

    private final KakaoMemberApiCaller kakaoMemberApiCaller;
    private final TokenManager tokenManager;

    public MemberOAuthService(KakaoMemberApiCaller kaKaoMemberApiCaller,
        TokenManager tokenManager) {
        this.kakaoMemberApiCaller = kaKaoMemberApiCaller;
        this.tokenManager = tokenManager;
    }

    /**
     * 토큰을 사용해서 사용자 정보를 가져오는 로직
     */
    public OAuthCommand.MemberInfo getMemberInfo(String accessToken) {
        JsonNode userInfo = kakaoMemberApiCaller.getMemberInfo(accessToken);
        return OAuthCommand.MemberInfo.from(userInfo);
    }
}

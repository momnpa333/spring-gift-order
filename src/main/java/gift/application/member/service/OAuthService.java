package gift.application.member.service;

import com.fasterxml.jackson.databind.JsonNode;
import gift.application.member.dto.OAuthCommand;
import gift.application.member.service.apicaller.KakaoMemberApiCaller;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    private final KakaoMemberApiCaller kakaoMemberApiCaller;

    public OAuthService(KakaoMemberApiCaller kaKaoMemberApiCaller) {
        this.kakaoMemberApiCaller = kaKaoMemberApiCaller;
    }

    /**
     * 인가 코드를 사용해서 토큰 가져오기
     */
    private String getAccessToken(OAuthCommand.Login command) {
        return kakaoMemberApiCaller.getAccessToken(command.authorizationCode());
    }

    /**
     * 토큰을 사용해서 사용자 정보를 가져오는 로직
     */
    public OAuthCommand.MemberInfo getMemberInfo(OAuthCommand.Login command) {
        String accessToken = getAccessToken(command);
        JsonNode userInfo = kakaoMemberApiCaller.getMemberInfo(accessToken);
        return OAuthCommand.MemberInfo.from(userInfo);
    }
}

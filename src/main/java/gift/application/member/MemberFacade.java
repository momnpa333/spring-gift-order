package gift.application.member;

import gift.application.member.dto.MemberCommand;
import gift.application.member.dto.OAuthCommand;
import gift.application.member.service.MemberService;
import gift.application.member.service.MemberKakaoService;
import gift.application.token.TokenManager;
import gift.application.token.dto.TokenSet;
import gift.model.token.KakaoToken;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MemberFacade {

    private final MemberService memberService;
    private final MemberKakaoService memberKakaoService;
    private final TokenManager tokenManager;

    public MemberFacade(MemberService memberService, MemberKakaoService memberKakaoService,
        TokenManager tokenManager) {
        this.memberService = memberService;
        this.memberKakaoService = memberKakaoService;
        this.tokenManager = tokenManager;
    }

    @Transactional
    public String socialLogin(Long memberId, OAuthCommand.Login command) {
        KakaoToken token = tokenManager.getTokenAndSaveByAuthorizationCode(memberId,
            command.authorizationCode());
        OAuthCommand.MemberInfo memberInfo = memberKakaoService.getMemberInfo(
            token.getAccessToken());
        MemberCommand.Create create = memberInfo.toCreateCommand();
        Pair<Long, String> memberIdAndJwt = memberService.socialLogin(create);
        return memberIdAndJwt.getSecond();
    }

}

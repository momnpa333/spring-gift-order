package gift.application.member;

import gift.application.member.dto.MemberCommand;
import gift.application.member.dto.OAuthCommand;
import gift.application.member.service.MemberService;
import gift.application.member.service.MemberOAuthService;
import gift.application.token.TokenManager;
import gift.application.token.dto.TokenSet;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MemberFacade {

    private final MemberService memberService;
    private final MemberOAuthService memberOAuthService;
    private final TokenManager tokenManager;

    public MemberFacade(MemberService memberService, MemberOAuthService memberOAuthService,
        TokenManager tokenManager) {
        this.memberService = memberService;
        this.memberOAuthService = memberOAuthService;
        this.tokenManager = tokenManager;
    }

    @Transactional
    public String socialLogin(OAuthCommand.Login command) {
        TokenSet tokens = tokenManager.getTokens(command.authorizationCode());
        OAuthCommand.MemberInfo memberInfo = memberOAuthService.getMemberInfo(tokens.accessToken());
        MemberCommand.Create create = memberInfo.toCreateCommand();
        Pair<Long, String> memberIdAndJwt = memberService.socialLogin(create);
        tokenManager.saveTokens(memberIdAndJwt.getFirst(), tokens);
        return memberIdAndJwt.getSecond();
    }

}

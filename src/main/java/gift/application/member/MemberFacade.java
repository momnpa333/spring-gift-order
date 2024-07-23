package gift.application.member;

import gift.application.member.dto.OAuthCommand;
import gift.application.member.service.MemberService;
import gift.application.member.service.OAuthService;
import org.springframework.stereotype.Component;

@Component
public class MemberFacade {

    private final MemberService memberService;
    private final OAuthService oAuthService;

    public MemberFacade(MemberService memberService, OAuthService oAuthService) {
        this.memberService = memberService;
        this.oAuthService = oAuthService;
    }

    public void login(OAuthCommand.Login command) {
        oAuthService.getUserInfo(command);
    }

}

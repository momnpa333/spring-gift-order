package gift.controller.member;

import gift.application.member.MemberFacade;
import gift.controller.member.dto.MemberResponse;
import gift.controller.member.dto.OAuthRequest;
import gift.global.auth.Authenticate;
import gift.global.auth.Authorization;
import gift.global.auth.LoginInfo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    private final MemberFacade memberFacade;

    public OAuthController(MemberFacade memberFacade) {
        this.memberFacade = memberFacade;
    }

    @PostMapping("/oauth/login")
    public ResponseEntity<MemberResponse.Login> login(
        @RequestBody @Valid OAuthRequest.LoginRequest request,
        @Authenticate LoginInfo loginInfo
    ) {
        var response = memberFacade.socialLogin(loginInfo.memberId(), request.toCommand());
        return ResponseEntity.ok(MemberResponse.Login.from(response));
    }
}

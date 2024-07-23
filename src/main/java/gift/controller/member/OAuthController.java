package gift.controller.member;

import gift.application.member.MemberFacade;
import gift.controller.member.dto.OAuthRequest;
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
    public ResponseEntity<Void> login(
        @RequestBody @Valid OAuthRequest.LoginRequest request
    ) {
        memberFacade.socialLogin(request.toCommand());
        return null;
    }
}

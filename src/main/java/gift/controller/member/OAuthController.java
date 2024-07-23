package gift.controller.member;

import gift.controller.member.dto.OauthRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    @PostMapping("/oauth/login")
    public ResponseEntity<Void> login(
        @RequestBody @Valid OauthRequest.LoginRequest request
    ) {
        return null;
    }
}

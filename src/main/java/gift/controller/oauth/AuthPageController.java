package gift.controller.oauth;

import gift.global.auth.Authorization;
import gift.model.member.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthPageController {

    @GetMapping("/oauth/login")
    public String admin() {
        return "oauthLoginPage";
    }

}

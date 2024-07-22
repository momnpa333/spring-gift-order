package gift.controller.member;

import gift.controller.member.dto.MemberRequest;
import gift.controller.member.dto.MemberResponse;
import gift.global.auth.Authenticate;
import gift.global.auth.Authorization;
import gift.global.auth.LoginInfo;
import gift.model.member.Role;
import gift.application.member.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
        @RequestBody @Valid MemberRequest.Register request
    ) {
        memberService.register(request.toCommand());
        return ResponseEntity.ok("User created successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponse.Login> login(
        @RequestBody @Valid MemberRequest.Login request
    ) {
        var response = memberService.login(request.toCommand());
        return ResponseEntity.ok(MemberResponse.Login.from(response));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return null;
    }

    @Authorization(role = Role.USER)
    @GetMapping("")
    public ResponseEntity<MemberResponse.Info> getUser(@Authenticate LoginInfo loginInfo) {
        var response = memberService.getUser(loginInfo.memberId());
        return ResponseEntity.ok(MemberResponse.Info.from(response));
    }

}

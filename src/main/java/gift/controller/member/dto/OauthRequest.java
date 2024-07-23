package gift.controller.member.dto;

import jakarta.validation.constraints.NotNull;

public class OauthRequest {

    public record LoginRequest(
        @NotNull
        String authorizationCode
    ) {

    }

}

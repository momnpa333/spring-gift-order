package gift.application.token.dto;

public record TokenSet(
    String accessToken,
    String refreshToken
) {

}

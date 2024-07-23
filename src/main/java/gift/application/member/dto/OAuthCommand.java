package gift.application.member.dto;

public class OAuthCommand {

    public record Login(String authorizationCode) {

    }
}

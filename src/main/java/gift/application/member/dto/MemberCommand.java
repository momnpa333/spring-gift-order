package gift.application.member.dto;

import gift.model.member.Member;
import gift.model.member.Role;

public class MemberCommand {

    public record Create(
        String email,
        String password,
        String name,
        Role role
    ) {

        public Member toEntity() {
            return new Member(null, email, password, name, role);
        }
    }

    public record Login(
        String email,
        String password
    ) {

    }

}

package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        var memberId = 1L;
        var member = new Member(memberId, "m1", Grade.VIP);
        memberService.join(member);
        var findMember =  memberService.findMember(memberId);
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}

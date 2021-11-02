package hello.core;

import hello.core.member.*;

public class MemberApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();

        var member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        var findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}

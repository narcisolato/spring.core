package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /*
        * 스프링은 ApplicationContext로부터 시작한다. 이게 스프링 컨테이너이다.
        * Appconfig에서 어노테이션 @Bean 등 기반으로 했으니까 new 어노테이션..으로 생성한다.
        * AnnotationConfigApplicationContext의 인자로 AppConfig를 넣어주면 App의 환경 정보를 가지고
        * 스프링이 어노테이션 붙은 것들은 스프링 컨테이너에다가 주입하여 관리해준다.
        */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 객체를 가져올 때는 객체 이름과 반환 타입으로 찾는다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        var member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        var findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}

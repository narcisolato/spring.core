package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(

//        이 어노테이션은 @Component가 붙은 클래스를 자동으로 스프링 빈에 붙여준다.
//        그리고 @Configuration은 들어가보면 @Component가 있다.
//        그런데 이전에 만들어둔 AppConfig, TestConfig 클래스는 @Configuration이 붙어있고, 이것의 Bean들이 자동등록되면 안된다.
//        그래서 아래 필터는 Configuration이 붙은 클래스의 Bean들이 자동등록되지 않도록 하기 위해 제외하는 필터다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),

        //        이건 member 패키지 안에 있는 것만 등록시키는 거임. 시간단축을 위해
        basePackages = "hello.core.member"

)

// 여기에는 뭐 해줄 게 없다. Config 안해도 @Component 붙은 클래스를 알아서 빈에 등록한다.
// 그런데 AppConfig에서 의존관계 주입을 안하면 그 클래스의 의존관계 주입은 어떻게 할까?
// 그건 자동 주입 @Autowired를 의존관계 주입 위에다 붙여주면 된다.
// 마치 ac.getBean(MemberRepository.class) 하듯이.
public class AutoAppConfig {

}

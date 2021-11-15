package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        var ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototype 1");
        var prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototype 2");
        var prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        //ac.close()는 호출되지 않는다.
        //호출되도록 하려면 prototypeBean1.destroy()로 직접 호출해줘야 한다.
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeTest.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeTest.destroy");
        }
    }
}

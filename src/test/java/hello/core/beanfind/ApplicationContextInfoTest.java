package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // 사실 junit5 버전?에서는 public 을 안써도 된다고 한다.
    @Test
    @DisplayName("모든 빈 출력하기")
    public void findAllBean() {
        var beanDefinitionNames = ac.getBeanDefinitionNames();
        // iter 쓰고 탭 누르면 바로 위 어레이의 foreach 문이 자동으로 만들어진다.
        for (String beanDefinitionName : beanDefinitionNames) {
            var bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈(사용자가 직접 정의한 빈) 출력하기")
     void findApplicationBean() {
        var beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 밑에 ac.getBeanDefinition()는 ac를 ApplicationContext 인터페이스로 선언하면 안된다.
            var beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                var bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", object = " + bean);
            }
        }
    }
}

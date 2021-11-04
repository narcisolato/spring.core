package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFIndTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName() {
        var bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 특정 하위 타입으로 지정하면 된다")
    void findBeanBySubType() {
        var bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType() {
        var beans = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beans.keySet()) {
            // 실제 테스트케이스를 짤 때는 이런 출력물을 남기면 안된다고 한다.
            // 자동 통과 시스템은 시스템이 해야하는 거라 눈으로 보고있으면 안된다.
            System.out.println("key = " + key + "value = " + beans.get(key));
        }
        assertThat(beans.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("최상위 Object 타입으로 모두 조회하기")
    void findAllBeanByObjectType() {
        var beans = ac.getBeansOfType(Object.class);
        for (String key : beans.keySet()) {
            System.out.println("key = " + key + "value = " + beans.get(key));
        }
    }

    @Configuration
    static public class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}

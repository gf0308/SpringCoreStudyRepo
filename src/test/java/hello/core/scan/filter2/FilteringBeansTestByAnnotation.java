package hello.core.scan.filter2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class FilteringBeansTestByAnnotation {

    @Test
    void test() {
        // 아래 스프링빈설정정보 Configer 대로 스프링컨테이너를 구동시키면, TestClassA는 스프링빈으로 등록되고 TestClassB는 등록되지 않을 것이다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(Configer.class);
        TestClassA beanA = ac.getBean("testClassA", TestClassA.class);
        assertThat(beanA).isNotNull();
//        assertThrows(
//                NoSuchBeanDefinitionException.class,
//                () -> ac.getBean("testClassA", TestClassA.class)
//        );

        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("testClassB", TestClassB.class)
        );
    }

    // 스프링컨테이너가 사용할 빈의존관계 환경설정 객체
    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeAnnotation.class),
            excludeFilters =  {
                    @Filter(type = FilterType.ANNOTATION, classes = MyExcludeAnnotation.class)
//                    @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = TestClassA.class) // FilterType 중 ASSIGNABLE_TYPE 을 이용해서 직접 필터링 대상 클래스를 지정해줄수있음
            }
    )
    static class Configer {
    }

}

// 사실 애노테이션 간에는 상속이란 개념이 없다; 즉 기본적인 자바 문법상에선 애노테이션 간의 상속이라는 것은 존재하지 않는다
// @Configuration 애노테이션이 그 안에 @Component 애노테이션을 포함하는 것, 그리고 이런 것을 인지하는 것은 가능하게 하는 것은 자바가 아닌 온전히 '스프링'의 기능이다.

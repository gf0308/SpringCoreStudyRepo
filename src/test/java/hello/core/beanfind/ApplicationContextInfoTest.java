package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ac.getBeanDefinition() 메서드는
 * ac를 AnnotationConfigApplicationContext 타입으로 받으면 사용가능하게 뜨지만,
 * 그냥 ApplicationContext 타입으로 받으면 뜨지 않아 사용할수가 없다..!
 * */

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

//    @BeforeEach
//    void beforeEach() {
//        ac = new AnnotationConfigApplicationContext(AppConfig.class);
//    }

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() { // JUnit5부턴 테스트메서드에 앞에 public 붙이지 않아도 되게 되었다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);

        }

    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 'beanDefinition' 이란 '빈 하나하나에 대한 정보', '빈 메타 데이터'를 말함
            // beanDefinition 에는 ROLE 이란게 있음 : 빈(bean) 의 사용용도를 지칭하는 것
            // - 크게 3가지가 있다 (이 중 1개는 잘 안씀 : ROLE_APPLICATION(0) / ROLE_SUPPORT(1) / ROLE_INFRASTRUCTURE(2) )
            // ROLE_APPLICATION : '애플리케이션 개발을 위해 개발자가 직접 만들어 등록한 빈' 을 뜻함 (or 라이브러리)
            // ROLE_INFRASTRUCTURE : '스프링이 스프링컨테이너 내부에서 사용하는 빈' 을 뜻함

            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " / object = " + bean);
            }

            /**
             * [ROLE_INFRASTRUCTURE 에 해당하는 스프링 빈] (스프링 내부적으로 생성해 사용하는 것)
             ** internalConfigurationAnnotationProcessor 객체
             ** internalAutowiredAnnotationProcessor 객체
             ** internalCommonAnnotationProcessor 객체
             ** internalEventListenerProcessor 객체
             ** internalEventListenerFactory 객체
             * */
        }

    }

}

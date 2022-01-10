package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest { // 상태가유지되는(stateful) 객체 인스턴스를 사용하면 무슨 문제가 발생할 수 있는지 테스트

    /**
     * 아래에서 현재 'TestConfig' 빈구성정보를 참고해 스프링빈을 등록한 스프링컨테이너 ac가 생겼다.
     * 그리고 이 스프링컨테이너 ac 안에 있는 스프링빈들은 '싱글톤'패턴으로 구현된 싱글톤 객체들이다
     * 이 동일한 참조값만을 매번 공유하는 싱글톤 객체를 사용하면 발생할수있는 문제점은 아래 예제와 같다
     * */
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B사용자가 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);     // statefulService1 스프링빈에서 주문한 금액은 10000원이고 이 10000원이 뜰거라고 예상하는데,
                                                    // 동일한 참조값을 이용해서 공유하기에 중간에 statefulService2인스턴스의 20000원 주문처리가 들어오면
                                                    // 이에 영향을 받아 버리고, 그 결과 여기서 20000원이 떠버리게 된다.

//        assertThat(statefulService1.getPrice()).isEqualTo(10000);  // userA에 대해선 10000원을 주문했는데, 그래서 statefulService1에서 getPrice하면 10000원이 떠야 할텐데, 오히려 이러면 틀린다고 나오고
//        assertThat(statefulService1.getPrice()).isEqualTo(20000); // 20000이 맞다고 오히려 나와버린다. => 실무에서, 실제 서비스에서 이렇게 되면 에러찾기도 정말어렵고, 비즈니스도 망하는 거다.
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
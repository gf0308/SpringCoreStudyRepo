package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);   // 여기서 AnnotationConfigApplicationContext의 파라미터로 투입되는 AppConfig: 스프링애플리케이션 구성에 필요한 환경설정정보들(객체 간 의존관계 등) 을 담고 있는 클래스
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);// getBean(꺼낼빈의이름, 해당빈의타입의 class정보)
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        // 18번째줄에서 만든 스프링 컨테이너에서
        // memberService 빈과 orderService빈을 꺼내왔다.

        // 이번엔 스프링컨테이너안에있는 MemberRepository를 직접 꺼내왔다
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);     // 서로다른 객체인 memberService와 orderService의 안에서, 동일한 memberRepository인스턴스를 반환했다. -> 즉 memberRepository가 여전히 싱글톤 상태 객체이다(서로 각각 new MemeberRepository() 를 했음에도 불구하고)
        System.out.println("memberRepository = " + memberRepository);    // 위 memberService와 orderService안에서 가져온 MemberRepository인스턴스와 완전히 동일한 인스턴스이다.

        Assertions.assertThat(memberService.getMemberRepository()).isEqualTo(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isEqualTo(memberRepository);

    }

//    @Test
//    void configurationTest() {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
//        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
//        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
//
//        MemberRepository memberRepository1 = memberService.getMemberRepository();
//        MemberRepository memberRepository2 = orderService.getMemberRepository();
//
//        System.out.println("memberService --> memberRepository = " + memberRepository1);
//        System.out.println("orderService --> memberRepository = " + memberRepository2);
//        System.out.println("memberRepository = " + memberRepository);
//
//        // MemoryMemberRepository 객체가 싱글톤으로 설계한것도 지금 아닌데, 싱글톤으로 생성되서 동일한 참조값을 제공되고 있다..!
//
//        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberService.getMemberRepository());
//        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberService.getMemberRepository());
//    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfigBean = ac.getBean(AppConfig.class);
        System.out.println("appConfigBean = " + appConfigBean.getClass());
    }




}

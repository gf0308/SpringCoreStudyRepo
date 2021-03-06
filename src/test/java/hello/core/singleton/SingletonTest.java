package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
        // 스프링 없는 순수 DI 컨테이너의 문제점을 살펴볼것
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
//        memberService1 = hello.core.member.MemberServiceImpl@17f7cd29   다른 참조값임을 알수있음
//        memberService2 = hello.core.member.MemberServiceImpl@7d8704ef

        // 근데 이런 상태의 문제는, 이를 수행해달라는 요청이 올때마다 매번 객체를 새로 생성하게 됨
        // -> 웹애플리케이션처럼 동시다수사용자가 많은 경우, 예를 들어 초당트래픽 TPS가 50000이 되면,
        // 1초마다 객체를 (최소) 50000개를 생성해야 된다는것 (실제로는 그 객체 만 아니라 다른 부가적인 것들도 생성하는것까지 생각하면) -> 엄청난 리소스 낭비
        // --> 해결방법은 해당객체를 딱 1개만 생성해놓고, 이를 공유해서 사용하도록 하는 것 => 이러면 1개만 생성해주면 됨! => 리소스 절약!

        //memberService1 != memberService2 확인 테스트
        assertThat(memberService1).isNotSameAs(memberService2);
    }


//    public static void main(String[] args) {
////        SingletonService singletonService1 = new SingletonService();    // SingletonService 객체를 싱글톤객체로 구현해놓았음 -> 이렇게 new로 따로 생성해주려고 하면
//                                                                            // "'SingletonService()' has private access in 'hello.core.singleton.SingletonService'" 가 뜸
//                                                                            // -> .getInstance() 로만 딱 1번 만들 수 있음
//
//    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
//        SingletonService singletonService = new SingletonService();
        // [컴파일오류] 당연히 'java: SingletonService() has private access in hello.core.singleton.SingletonService' 컴파일 오류가 발생함 (컴파일오류가 이래서 좋은 것이다 : '문제가있는코드일경우 컴파일단계서 부터 알아낼수있다는것' 이 엄청난 유익이다; 만약 문제있는 코드인데 실제로 실행되버리면 더 문제가 되므로

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
//        instance1 = hello.core.singleton.SingletonService@305b7c14
//        instance2 = hello.core.singleton.SingletonService@305b7c14  ==> 동일한 'SingletonService@305b7c14' 참조값이 표시 : '동일한 객체 인스턴스'가 반환됨

        assertThat(singletonService1).isSameAs(singletonService2);
        // isSameAs : 항등연산자 격(==) : 동일한존재인지(동일메모리값) 체크
        // isEqualTo : equals() 메서드 격 (.equals) : 같은 의미를 나타내는 값인지 체크
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springCotainer() {
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 동일함을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
//        memberService1 = hello.core.member.MemberServiceImpl@25e2ab5a
//        memberService2 = hello.core.member.MemberServiceImpl@25e2ab5a
//        => 스프링컨테이너에서 이 스프링빈을 요청하면 '동일한' 빈을 반환해줌 -> 스프링컨테이너가 싱글톤방식으로 객체를 관리하고 있음을 알 수 있음!

//        memberService1 == memberService2 확인 테스트
        assertThat(memberService1).isSameAs(memberService2); // 동일한지 테스트로 확인 => ok
    }
}

package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너") // 스프링 없는 순수 DI 컨테이너의 문제점을 살펴볼것
    void  pureContainer() {
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
}

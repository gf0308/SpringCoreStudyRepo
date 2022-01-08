package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회") // 구체적인 타입으로 조회하는것도 가능; but (앞서 말했듯) 인터페이스에 의존해야지 구체클래스에 의존하면 안되므로, 이건 안쓰는게 좋음(간혹 어쩔수없을때도있긴함)
    void findBeanName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 테스트 케이스는 반드시 '실패 케이스'도 만들어보아야 함! : 실패케이스도 시나리오대로 실패로 돌아가야, 지금 테스트 하는 기능이 개발자의 시나리오대로 전부 기능하게 됨을 확인 가능
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanNameX() {
//        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class); // beanFactory 내에 이런 bean이 없음 -> "NoSuchBeanDefinitionException" 예외 발생

        // 예외가 터질것이라고 예상해서 진짜로 예외가 터지는지 확인하는 테스트 로직
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));  // 'NoSuchBeanDefinitionException' 에러가 터질것이다(터져야 정상이다) 란 것

    }
}

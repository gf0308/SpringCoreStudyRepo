package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// 현대 애플리케이션 개발 상황에서, 테스트코드 작성을 하며 개발하는것은 선택사항이 아니다, 필수다.

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
   /**
    * 위 코드 설계의 문제점
    * - OCP 원칙을 준수할 수가 없는 구조다 : "다른 저장소객체로 변경하려고 할 때"
    * - DIP 원칙을 준수할 수가 없다 : "의존관계가 인터페이스 뿐만 아니라 구현부까지 모두 의존하고 있는 문제점이 있음"
    * */


}

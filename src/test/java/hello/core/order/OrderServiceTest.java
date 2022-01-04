package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl(memberRepository);
    OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);

    @Test
    void createOrder() {
        Long memberId = 1L;     // 사실 "long memberId = 1L;" 으로 primitive type 인 long 으로 해줘도 똑같긴 하다, 다만 다른점이 있는데 long으로 하면 null을 넣을 수 없지만, Long으로 하면 null 도 넣을 수 있다는 것이다.
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);        // 'org.assertj.core.api' 의 Assertions 를 써야 메서드체인에서 편하게 테스트를 만들 수 있다..?
        //Assertions.assertEquals(order.getDiscountPrice(), 1000); // 이 방식도 가능하긴 함('org.junit.jupiter.api.Assertions' 것으로 사용하는 assertEquals 로

    }


}

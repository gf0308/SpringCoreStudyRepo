package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    // 어떤 참조타입 객체 필드에 final 이 적용되어있으면, 무조건 'new 타입명()' 식으로 기본생성해서 할당하는 방식이든, 생성자로 할당하는 방식이든 해야 함
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired // 의존관계 설정해주기를 위해 @Autowired로 자동주입 을 설정해줌
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // => 이렇게 바꾸고나니, 본 클래스가 오로지 인터페이스에만 의존하고 있는(인터페이스 관한 코드로만 이뤄진) 코드가 되었다 : 철저히 인터페이스에만 의존하는 클래스가 되었다
    // ==> discountPolicy 로 'FixDiscountPolicy' 가 올지 'RateDiscountPolicy' 가 올지 아무것도 몰라도 이 코드에서 다 받아낼수있고 들어가야할 변수부분에 잘만 들어갈 수 있음 -> 구현체 객체를 뭐든 투입해도 이곳 코드의 수정은 걱정할필요없이 맘껏 이것저것 투입할 수 있음(유지보수의 굉장한 편리)
    // : 이처럼 '인터페이스'로만 협력관계를 구성해놓으니, "객체와 객체간 협력관계를 그대로 재사용할수가 있다"
    // --> 제대로 객체지향적인, 객체지향적으로 훌륭한 구현/설계가 됐다.

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();       // => 사실 이러한 상황은, OrderServiceImpl 이란 배우(구현체)가 RateDiscountPolicy/FixDiscountPolicy 란 배우(구현체)를 직접 초빙하고 있는 것과 같은 상황이다. (초빙은 '감독'이 해야 하는데,감독이라는 다른 역할이 그걸 수행해야 하는데, 그리고 배우는 자기 역할(인터페이스)에만 집중해야 하는데, 다른 배우 초빙이라는 또 다른 책임(구체적인것)까지 수행하고 있는 격인 것이다.)
    // -> 이렇게 기존 코드를 직접 변경해버리면 -> OCP 를 위반함
    // + 구체화를 의존하고 있음(구현체를 직접 의존하고 있음) -> DIP 를 위반함
    // ==> 그러면 이를 해결할수있는 방법은 무엇이란 건가? : 아래처럼 코드를 바꾸기
    //          ↓

    //private DiscountPolicy discountPolicy;      // -> 근데 이러면 실체가 담기지 않은 discountPolicy이기때문에, 이를 사용하면 NullPointerException 이 뜰게 뻔함 -> 어떡해야하나?
    //==>  누군가가 이곳 OrderServiceImpl에 위 discountPolicy에다가 구현체객체를 생성해서 넣어준다면, 위처럼 '실체를 할당하는 부분'이 없는 상태에서도 (NullPointerException 발생 없이) 이 객체를 제대로 이용할 수 있게 됨
    // -> 이 '구현체 객체'를 대신 생성해서 대신 넣어주는 고마운 키다리아저씨같은 존재가, 'DI 컨테이너' 다!

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 지금 현재 Order 객체 내에서, discount(할인)에 대한 부분은 이 discount를 계산하는 DiscountPolicy 객체에게 전적으로 맡겨버렸고,
                                                                        // 그 결과만 바로 받아서 이용하고 있다 (즉 구체적인 discount를 계산해내는 작업을 여기 Order 쪽에서 하지 않게 했음 => "단일책임원칙(SRP) 를 잘 지키도록 한 것"
                                                                        // (장점: 할인에 변경이 있으면, 할인쪽(DiscountPolicy 객체내부) 만 고치면 됨, 여긴 일절 코드수정할일 없음)
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}

/**
 * "관심사의 분리"
 * - '배우'는 본인의 역할인 '배역을 수행하는 것' 에만 집중해야 한다.
 * - 디카프리오는 어떤 여자 주인공이 선택되더라도 똑같이 공연을 할 수 있어야 한다 (누구 선택되든지는 본인이 알바 아님; 본인이 직접 선택할 수도 없음; 그저 자기 역할만 잘 수행하면 됨)
 * - 공연을 구성하고, 담당 배우를 섭외하고, 역할에 맞는 배우를 지정하는 책임을 담당하는 별도의 '공연 기획자' 가 있어야 함
 * -> 이 '공연 기획자' 역할이 존재하고 이게 제대로 일함으로써, 배우는 배우 역할을 수행하는 자기 일에만 집중하면 된다(어떤 배우를 섭외해야할지 등은 생각할 필요도 없음)
 *       (DI 컨테이너)
 *
 * */
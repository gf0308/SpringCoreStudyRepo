package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 지금 현재 Order 객체 내에서, discount(할인)에 대한 부분은 이 discount를 계산하는 DiscountPolicy 객체에게 전적으로 맡겨버렸고,
                                                                        // 그 결과만 바로 받아서 이용하고 있다 (즉 구체적인 discount를 계산해내는 작업을 여기 Order 쪽에서 하지 않게 했음 => "단일책임원칙(SRP) 를 잘 지키도록 한 것"
                                                                        // (장점: 할인에 변경이 있으면, 할인쪽(DiscountPolicy 객체내부) 만 고치면 됨, 여긴 일절 코드수정할일 없음)
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}

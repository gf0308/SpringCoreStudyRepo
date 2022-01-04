package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;   // 실제로 맞는지 테스트해봐야함(이런 비즈니스 로직은 특히)
        } else {
            return 0;
        }
    }
}

package hello.core.discount;


import hello.core.member.Member;

// 할인정책 인터페이스
public interface DiscountPolicy {

    /**
     * @return 할인대상금액
     * */
    int discount(Member member, int price);

}

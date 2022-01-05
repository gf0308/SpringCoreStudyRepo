package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * AppConfig
 * : 애플리케이션에 대한 환경을 구성하는건 여기서 다 하는 것이다
 *
 * [여기 AppConfig 가 하는 일]
 * 1. 애플리케이션의 실제 동작에 필요한 '구현 객체'들을 생성한다.
 * - MemberServiceImpl
 * - MemoryMemberRepository
 * - OrderServiceImpl
 * - FixDiscountPolicy
 *
 * 2. 객체와 객체를 연결해준다
 *  : AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)값을 생성자를 통해서 (해당객체에) 주입(연결)해준다.
 * - MemberServiceImpl -> MemoryMemberRepository
 * - OrderServiceImpl -> 'MemoryMemberRepository', 'FixDisCountPolicy'
 * */
    public class AppConfig {

        public MemberService memberService() {
            return new MemberServiceImpl(memberRepository());     // MemberServiceImpl 인스턴스를 생성할 때, MemberServiceImpl의 생성을 위해 필요한 객체를 MemberServiceImpl의 생성자를 통해서 넣어서 이용하고 있다
                                                                  // 이것처럼 '생성자를 통해 필요한 객체를 넣어주고 있는 것' 을 : 생성자 주입(생성자 인젝션: constructor injection) 이라고 함
        }

        public OrderService orderService() {
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }

        private MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }

        public DiscountPolicy discountPolicy() {
//            return new FixDiscountPolicy();
            return new RateDiscountPolicy();
        }

    }

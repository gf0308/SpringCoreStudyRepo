package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 보통 구현체 클래스에 '인터페이스명+Impl' 식으로 붙여주는 경우를 많이 볼수있는데, '관례상' 구현체가 1개 만 있는 경우엔 '인터페이스명+Impl' 식으로만 이름을 붙여서 만들어주기도 한다.
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;    // -> 이렇게 하면 MemberServiceImpl이 어떤 구현체를 직접적으로 의존하는 것 없이, 추상에만(인터페이스: MemberRepository) 의존하고 있다

    @Autowired  // ac.getBean(MemberRepository.class) 한 것을 주입하는것과 같음 // 의존관계주입을 자동으로 해주는 @Autowired : (스프링컨테이너 안의) 해당 타입/이름 등의 의존객체(스프링빈)을 자동으로 넣어준다
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //  이전의 AppConfig에서는 @Bean으로 직접 설정정보를 작성했었고, 의존관계도 직접 명시했었음; 이제는 이런 설정정보 자체가 없기 때문에, 의존관계 주입도 이 클래스안에서 해결해야 하는 것이 된것이다
    // @Autowired는 의존관계(의존성;의존객체;주입되야할스프링빈)를 자동으로 주입해준다.

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // memberRepository 은 타입을 MemberRepository 인터페이스로 두고 있음 -> MemberRepository 인터페이스에 의존하고 있는 것이므로, 괜찮음
    // 하지만, memberRepository 에 실제객체를 할당하는 부분이, 실제구현체인 'MemoryMemberRepository' 를 의존하고 있음
    // => 다른 객체로 교체해줘야 한다면, 매번 이 코드를 직접 건드릴수 밖에 없는 상황
    // ==> '추상화' 에도 의존하고 있고, '구현'에도 의존하고 있는 것 (되게 안 좋은 상황) : DIP 를 위반하고 있는 것

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}

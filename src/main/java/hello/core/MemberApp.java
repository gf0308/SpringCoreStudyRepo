package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();    // appConfig.memberService()로 MemberService를 얻어냈으니, 기존 이 직접 만들어내는 코드는 필요없음

        //'org.springframework.context.ApplicationContext'
        // 스프링은 모든 게 'ApplicationContext' 라는 것으로부터 시작된다 (이게 스프링 컨테이너; DI 컨테이너 라고 보면 됨)
        //AnnotationConfigApplicationContext
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);  // Bean은 객체(자바객체)를 말한다
                                                                                                                // 스프링컨테이너(DI 컨테이너)에서 만들어지는 객체 Bean의 이름은 그 객체생성메서드명으로 된다.(정확히말하면, key는 메서드명 / value 는 메서드내부구현의 리턴값 으로 함)
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        // ctrl 만 누르고 메서드 클릭하면 메서드의 인터페이스 부로만 이동하지만, ctrl+alt를 누르고 클릭하면 구현부까지 바로 이동함

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }

}

// 프로젝트가 빌드될 때 'main' 에 대한 부분만 빌드가 이뤄지고, 'test' 쪽은 빌드 대상에서 포함되지 않고 빠진다.
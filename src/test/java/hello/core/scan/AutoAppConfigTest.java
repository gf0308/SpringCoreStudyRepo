package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.scan.filter3.SpringConfigerWithCompFilter;
import hello.core.scan.filter3.TestBeanA;
import hello.core.scan.filter3.TestBeanB;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
//        MemberService memberService = ac.getBean(MemberService.class);
//        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

}

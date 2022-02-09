package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // 자동으로 스프링빈으로서 끌어올려 등록되게 해주는 컴포넌트스캔 : @Component가 붙은 클래스 들을 전부 객체생성해 스프링빈으로 등록한다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 기존의 @Configuration 클래스인 AppConfig 도 덩달아 등록이 되어선 안되므로, 배제처리함
)
public class AutoAppConfig {

}

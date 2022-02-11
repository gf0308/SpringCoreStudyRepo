package hello.core.scan.filter3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
// 스프링컨테이너가 컴포넌트를 스캔하는것에 관여할 수 있다(@ComponentScan 기능으로)
@ComponentScan(
//        includeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = TestBeanA.class),
//        excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = TestBeanB.class)
        includeFilters = @Filter(type = FilterType.ANNOTATION, classes = TestIncludeComponent.class),
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = TestExcludeComponent.class)
)
public class SpringConfigerWithCompFilter {
}

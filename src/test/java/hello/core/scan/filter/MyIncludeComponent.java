package hello.core.scan.filter;

import java.lang.annotation.*;

// 커스텀 애노테이션을 만들기 위해 클래스에 필요한 애노테이션 3개
@Target(ElementType.TYPE) // 이 TYPE 부분이 중요하다 - 이 애노테이션이 타입에 붙냐 필드에 붙냐, 이런걸 설정하는 것 : 타입이랬으니 클래스 레벨에 붙는다는 것임
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {


}

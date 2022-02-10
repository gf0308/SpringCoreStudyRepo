package hello.core.scan.filter2;

import java.lang.annotation.*;

// 커스텀으로 애노테이션을 생성하기 위해 필요한 애노테이션 3종: @Target/@Retention/@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeAnnotation {
}

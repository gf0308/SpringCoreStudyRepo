package hello.core.scan.filter3;

import org.springframework.stereotype.Component;

@TestExcludeComponent
@Component  // @Component처리가 된 컴포넌트 클래스도, 컴포넌트스캔 filter에서 exclude처리를 하면 빈등록에서 제외될수있음
public class TestBeanB {
}

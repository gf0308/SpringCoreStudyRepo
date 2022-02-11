package hello.core.scan.filter3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRun {

    @Test
    void testMethod() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfigerWithCompFilter.class);
        TestBeanA testBeanA = ac.getBean("testBeanA", TestBeanA.class);

        assertThat(testBeanA).isNotNull();
        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("testBeanB", TestBeanB.class)
        );
    }

}

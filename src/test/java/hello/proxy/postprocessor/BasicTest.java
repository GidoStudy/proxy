package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
public class BasicTest {

    @Test
    void basicConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                BasicConfig.class);
        // A는 빈으로 등록된다
        A a = applicationContext.getBean("beanA", A.class);
        a.helloA();
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,()->
                applicationContext.getBean(B.class));
    }


    @Configuration
    static class BasicConfig{
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }

    static class A{
        public void helloA() {
            log.info("hello A");
        }

        public void helloB() {
            log.info("hello B");
        }
    }

    static class B{
        public void helloA() {
            log.info("hello A");
        }

        public void helloB() {
            log.info("hello B");
        }
    }

}

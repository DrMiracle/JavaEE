package library;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MyLibraryAutoConfig {

    @Bean
    MyLibraryClass MyLibraryClass(){
        return new MyLibraryClass();
    }

    @Bean
    @ConditionalOnMissingBean(MyLibraryClass.class)
    public OnMissingBean OnMissingBean() {
        return new OnMissingBean();
    }

    @Bean
    @ConditionalOnBean(MyLibraryClass.class)
    public OnBean OnBean() {
        return new OnBean();
    }

    @Bean
    @ConditionalOnProperty(prefix = "my", name="property", havingValue ="dunno")
    public OnProperty1 OnProperty1() {
        return new OnProperty1();
    }

    @Bean
    @ConditionalOnProperty(prefix = "my", name="property", havingValue ="nno")
    public OnProperty2 OnProperty2() {
        return new OnProperty2();
    }
}

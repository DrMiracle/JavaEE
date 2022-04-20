package client;

import library.MyLibraryAutoConfig;
import library.OnProperty1;
import library.OnProperty2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("library")
@Configuration(proxyBeanMethods = false)
public class MyConfiguration {

    @Bean
    @ConditionalOnBean(OnProperty1.class)
    public Property1 Property1(){
        return new Property1();
    }

    @Bean
    @ConditionalOnBean(OnProperty2.class)
    public Property2 Property2(){
        return new Property2();
    }

    @Bean
    @ConditionalOnMissingBean(OnProperty1.class)
    public Property1Missing Property1Missing(){
        return new Property1Missing();
    }

    @Bean
    @ConditionalOnMissingBean(OnProperty2.class)
    public Property2Missing Property2Missing(){
        return new Property2Missing();
    }
}

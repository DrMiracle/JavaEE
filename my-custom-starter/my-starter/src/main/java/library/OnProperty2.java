package library;

import org.springframework.beans.factory.InitializingBean;

public class OnProperty2 implements InitializingBean, ConditionalInterFace {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("On property 2");;
    }
}
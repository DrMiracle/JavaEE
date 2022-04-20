package library;

import org.springframework.beans.factory.InitializingBean;

public class OnProperty1 implements InitializingBean, ConditionalInterFace {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("On property 1");;
    }
}

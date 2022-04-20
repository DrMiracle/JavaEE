package library;

import org.springframework.beans.factory.InitializingBean;

public class OnMissingBean implements InitializingBean, ConditionalInterFace {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("On missing bean");;
    }
}

package library;

import org.springframework.beans.factory.InitializingBean;

public class OnBean implements InitializingBean, ConditionalInterFace{

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("On bean");;
    }
}

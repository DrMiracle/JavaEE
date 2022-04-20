package client;

import org.springframework.beans.factory.InitializingBean;

public class Property2Missing implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("On property 2 missing");;
    }
}

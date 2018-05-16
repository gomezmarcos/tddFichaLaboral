package org.mog.conf;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Utilitario implements ApplicationContextAware {

    private static ApplicationContext ac;

    public static MyContainer myContainer(){
        return new MyContainer(ac);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }
}

package org.mog.conf;

public class Utilitario {

    private static MyContainer container = null;

    public static MyContainer myContainer(){
        if (container == null)
            container = new MyContainer();
        return container;
    }

}

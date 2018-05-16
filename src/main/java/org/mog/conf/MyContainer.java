package org.mog.conf;

import org.mog.bo.Fichero;
import org.mog.plugin.repo.FicheroRepoInMemoryCrud;
import org.mog.plugin.repo.FicheroRepoInMemory;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class MyContainer {
    private static MyContainer instance;
    private static Map<String, Object> container = new HashMap();

    private static ApplicationContext context;

    public MyContainer(ApplicationContext context) {
        this.context = context;
        FicheroRepoInMemoryCrud fichaCrudRepo = (FicheroRepoInMemoryCrud) context.getBean("ficheroRepoInMemoryCrud");
        FicheroRepoInMemory ficherRepo = context.getBean(FicheroRepoInMemory.class, fichaCrudRepo);
        save("ficheroRepo", ficherRepo);
        save("fichero", new Fichero(ficherRepo));
    }

    public Object find(String name) {
        String key = container.keySet().stream().filter(n -> n.equals(name)).findFirst().orElseThrow(RuntimeException::new);
        return container.get(key);
    }

    public void save(String name, Object objectToStore) {
        container.put(name, objectToStore);
    }

}

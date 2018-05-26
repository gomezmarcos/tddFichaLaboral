package org.mog.conf;

import org.mog.bo.Fichero;
import org.mog.plugin.fe.fichero.FrontEndFicheroAdapter;
import org.mog.plugin.repo.FicheroRepoStub;
import org.mog.plugin.repo.FicheroRepo;

import java.util.HashMap;
import java.util.Map;

public class MyContainer {
    private static Map<String, Object> container = new HashMap();

    public MyContainer() {
        FicheroRepo ficheroRepo = new FicheroRepoStub();
        save("fichero", new Fichero(ficheroRepo));

        save( "ficheroAdapter",new FrontEndFicheroAdapter((Fichero) container.get("fichero")));
    }

    public Object find(String name) {
       // String key = container.keySet().stream().filter(n -> n.equals(name)).findFirst().orElseThrow(RuntimeException::new);

        return container.get(name);
    }

    public void save(String name, Object objectToStore) {
        container.put(name, objectToStore);
    }

}

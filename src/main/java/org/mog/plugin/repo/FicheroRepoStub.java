package org.mog.plugin.repo;

import org.mog.bo.Ficha;

import java.util.ArrayList;
import java.util.List;

public class FicheroRepoStub implements FicheroRepo {
    List<Ficha> baseDatos = new ArrayList();
    int oid = 0;
    @Override
    public Ficha persistir(Ficha ficha) {
        ficha.setOid(Long.valueOf(oid++));
        baseDatos.add(ficha);
        return ficha;

    }

    @Override
    public List<Ficha> findAll() {
        return baseDatos;
    }
}

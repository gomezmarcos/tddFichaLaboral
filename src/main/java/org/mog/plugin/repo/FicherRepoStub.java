package org.mog.plugin.repo;

import org.mog.bo.Ficha;

import java.util.ArrayList;
import java.util.List;

public class FicherRepoStub implements FicheroRepo {
    List<Ficha> baseDatos = new ArrayList();
    @Override
    public void persistir(Ficha ficha) {
        baseDatos.add(ficha);

    }

    @Override
    public List<Ficha> findAll() {
        return baseDatos;
    }
}

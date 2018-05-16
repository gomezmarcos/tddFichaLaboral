package org.mog.plugin.repo;

import org.mog.bo.Ficha;

import java.util.List;

public interface FicheroRepo {
    void persistir(Ficha ficha);
    List<Ficha> findAll();
}

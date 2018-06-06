package org.mog.plugin.repo;

import org.mog.bo.Ficha;

import java.util.List;

public interface FicheroRepo {
    Ficha persistir(Ficha ficha);
    List<Ficha> findAll();
}

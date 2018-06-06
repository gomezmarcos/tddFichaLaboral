package org.mog.plugin.repo;

import org.mog.bo.Ficha;
import org.mog.plugin.repo.entity.FichaPersistente;

import java.util.List;

public class FicheroRepoInMemory implements FicheroRepo {
    private FicheroRepoInMemoryCrud fichaCrudRepo;

    public FicheroRepoInMemory() {
    }

    public FicheroRepoInMemory(FicheroRepoInMemoryCrud repo) {
        this.fichaCrudRepo = repo;
    }

    @Override
    public Ficha persistir(Ficha ficha) {
        FichaPersistente f = map(ficha);
        //fichaCrudRepo.save(f);
        return null;

    }

    private FichaPersistente map(Ficha ficha) {
        FichaPersistente fichaPersistente = new FichaPersistente();
        fichaPersistente.time = ficha.getTime();
        fichaPersistente.date = ficha.getDate();
        fichaPersistente.sid = ficha.getSid();
        fichaPersistente.type = ficha.getType();
        return fichaPersistente;
    }

    private Ficha map(FichaPersistente ficha) {
        Ficha ficha1 = new Ficha(ficha.type, ficha.sid, ficha.date, ficha.time);
        ficha1.setOid( ficha.oid);
        return ficha1;
    }


    @Override
    public List<Ficha> findAll() {
        //return StreamSupport.stream(fichaCrudRepo.findAll().spliterator(), false)
         //       .map(f -> map(f))
          //      .collect(Collectors.toList());
        return null;

    }
}

package org.mog.plugin.repo;

import org.mog.plugin.repo.entity.FichaPersistente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "ficheroRepoInMemoryCrud")
public interface FicheroRepoInMemoryCrud extends CrudRepository<FichaPersistente, Long> {
}

package org.piva.fisd.database.repository;

import java.util.List;
import java.util.Optional;


public interface CrudRepository<T, ID> {

    Optional<T> getById(ID id);

    List<T> getAll();

    boolean update(T type);

    boolean delete(T type);

}

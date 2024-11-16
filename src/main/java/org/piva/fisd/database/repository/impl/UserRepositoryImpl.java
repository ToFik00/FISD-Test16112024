package org.piva.fisd.database.repository.impl;

import org.piva.fisd.database.entity.User;
import org.piva.fisd.database.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements CrudRepository<User, Long> {

    @Override
    public Optional<User> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public boolean update(User type) {
        return false;
    }

    @Override
    public boolean delete(User type) {
        return false;
    }
}

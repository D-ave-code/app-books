package com.distribuida.servicios;

import com.distribuida.db.Authors;

import java.util.List;

public interface AuthorsRepository {

    public Authors findById(Long id);

    public void delete(Long id);
    public Authors update (Authors b, Long id);
    public Authors create(Authors authors);
}

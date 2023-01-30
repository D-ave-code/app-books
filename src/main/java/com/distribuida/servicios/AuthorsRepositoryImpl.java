package com.distribuida.servicios;

import com.distribuida.db.Authors;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
@Transactional
public class AuthorsRepositoryImpl implements AuthorsRepository {

@Override
    public Authors findById(Long id) {
    try {
        Authors a = Authors.findById(id);
        return a;
    }catch (Exception ex){
        return null;
    }

    }
    @Override
    public void delete(Long id) {

            Authors.deleteById(id);




    }

@Override
    public Authors create(Authors b) {
    Authors.persist(b);
    return b;
    }

    @Override
    public Authors update(Authors authors,Long id) {

         Authors.update("first_name = :first_name , last_name = :last_name where id = :id",
                Parameters.with("first_name",authors.getFirst_name())
                        .and("last_name",  authors.getFirst_name())
                        .and("id",  authors.getId()));
        Authors author = findById(id);
        return  author;

    }
}

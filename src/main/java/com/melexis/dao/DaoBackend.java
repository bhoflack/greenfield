package com.melexis.dao;

import java.util.List;

public interface DaoBackend<T> {

    /**
     * Execute a query and return a list with the result.
     * @param query a string containing the query.
     * @return a list containing the result.
     **/
    List<T> executeQuery(final String query);


    /**
     * Update the entity
     * @param entity the entity to be updated in the repository.
     **/
    void update(final T entity);
}
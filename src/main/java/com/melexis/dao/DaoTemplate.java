package com.melexis.dao;

import java.util.List;

import com.melexis.FunctionBody;

public final class DaoTemplate<T> {

    private final DaoBackend<T> backend;

    public DaoTemplate(final DaoBackend<T> backend) {
        this.backend = backend;
    }

    /**
     * Execute query,  invoke body with the first result and
     * update the data in the db.
     * @param query The query to be executed.
     * @param body The body of the function.
     **/
    public final T withSingleResult(final String query, final FunctionBody<T> body) {
        final List<T> result = backend.executeQuery(query);
        final T first = result.get(0);

        return body.invoke(first);
    }

}
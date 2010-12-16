package com.melexis.dao;

import java.util.List;

import com.melexis.FunctionBody;

public final class DaoTemplate<T> {

    private final DaoBackend<T> backend;

    public DaoTemplate(final DaoBackend<T> backend) {
        this.backend = backend;
    }

    /**
     * Execute query,  invoke body for each result and
     * update the data in the db.
     * @param query The query to be executed.
     * @param body The body of the function.
     * @return the number of updated items.
     **/
    public final int withResult(final String query, final FunctionBody<T> body) {
        final List<T> result = backend.executeQuery(query);

        // iterate all results
        for (T item : result) {
            final T r = body.invoke(item);
            backend.update(r);
        }

        return result.size();
    }

}
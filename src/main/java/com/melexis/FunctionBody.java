package com.melexis;

public interface FunctionBody<T> {

    /**
     * Invoke a pure(-ish) function.
     * @param entity the entity to work on
     * @return the updated entity
     **/
    T invoke(T entity);
}
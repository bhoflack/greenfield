package com.melexis.dao;

import java.util.List;
import org.junit.*;
import com.melexis.FunctionBody;

import static org.junit.Assert.assertEquals;
import static java.util.Arrays.asList;

public class DaoTemplateTest {

    // Mock class that represents an entity.
    private final static class Foo {

        public String name;

        @Override public boolean equals(Object other) {
            if (!(other instanceof Foo)) {
                return false;
            }

            final Foo o = (Foo) other;

            return (o.name != null)? o.name.equals(name): name == null;
        }

    }

    // Mock class that represents a DaoBackend.
    private final static class FooDao implements DaoBackend<Foo> {

        public List<Foo> entities;

        public FooDao() {
            entities = asList(new Foo());
        }

        @Override public List<Foo> executeQuery(final String q) {
            return entities;
        }

        @Override public void update(final Foo entity) {
            entities.set(0, entity);
        }
    }

    private FooDao backend;
    private DaoTemplate<Foo> dao;

    @Before public void setUp() {
        backend = new FooDao();
        dao = new DaoTemplate<Foo>(backend);
    }

    @Test public void singleResult() {
        dao.withSingleResult("from Foo where id=1", new FunctionBody<Foo>() {

                @Override public Foo invoke(Foo foo) {
                    foo.name = "blaat";
                    return foo;
                }
            });

        assertEquals("blaat", backend.entities.get(0).name);
    }
}
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
        public int age;

        @Override public boolean equals(Object other) {
            if (!(other instanceof Foo)) {
                return false;
            }

            final Foo o = (Foo) other;

            return (o.name != null)? o.name.equals(name): name == null;
        }

        @Override public String toString() {
            return name;
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
        final int updated = dao.withResult("from Foo where id=1", new FunctionBody<Foo>() {

                @Override public Foo invoke(final Foo foo) {
                    final Foo bar = new Foo();
                    bar.name = "blaat";
                    return bar;
                }
            });

        assertEquals(1, updated);
        assertEquals("blaat", backend.entities.get(0).name);
    }
}
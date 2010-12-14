package com.melexis;

import java.util.Date;
import java.util.HashMap;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public final class ParameterPage extends WebPage {

    private static final long serialVersionUID = 1L;

    /**
     * This factory should be used by other classes.
     * @param name the person's name.
     * @param today the date today.
     **/
    public final static ParameterPage newInstance(final String name, final Date today) {
        final HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        parameters.put("date", today.toString());

        return new ParameterPage(new PageParameters(parameters));
    }

    /**
     * This class accepts the following parameters:
     * name: a string containing the person's name.
     * date: a date containing the current date.
     * other parameters are ignored.
     * When none of these parameters are provided a RuntimeException
     * is thrown.
     **/
    public ParameterPage(final PageParameters pp) {
        if (!pp.containsKey("name") ||
            !pp.containsKey("date")) {
            throw new RuntimeException("This page requires parameters name and date.");
        }

        add(new Label("name", pp.getString("name")));
        add(new Label("date", pp.getString("date")));
    }

}
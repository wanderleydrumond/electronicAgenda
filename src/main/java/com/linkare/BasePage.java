package com.linkare;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class BasePage extends WebPage {

    private static final long serialVersionUID = 502327979204314267L;

    public BasePage() {
        String userName = (String) getSession().getAttribute("UserName");
        if (userName == null) {
            setResponsePage(Login.class);
            return;
        }
        add(new Link<Void>("logout") {
            private static final long serialVersionUID = 572300979207854267L;

            @Override
            public void onClick() {
                getSession().invalidate();
                setResponsePage(Begin.class);
            }
        });
    }
}
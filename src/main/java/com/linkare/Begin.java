package com.linkare;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class Begin extends BasePage {

    private static final long serialVersionUID = 502327979204314267L;

    public Begin() {
        Label labeWelcomeMessage = new Label("welcomeMessage", Model.of("Welcome to eletronic agenda"));
        add(labeWelcomeMessage);
    }
}
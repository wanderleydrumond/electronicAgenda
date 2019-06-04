package com.linkare;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class Login  extends WebPage {

    private static final long serialVersionUID = 200027314207854267L;

    public Login() {
        final TextField<String> tfUsername = new TextField<String>("username", new Model<String>());
        final PasswordTextField tfPassword = new PasswordTextField("password", new Model<String>());

        final Label loginErrorMessage = new Label("loginErrorMessage", Model.of("Error: login not completed"));
        loginErrorMessage.setOutputMarkupId(true).setVisible(false);

        Form loginForm = new Form<String>("loginForm"){
            private static final long serialVersionUID = 301027316208854267L;

            @Override
            protected void onSubmit() {
                String username = tfUsername.getModelObject();
                String password = tfPassword.getModelObject();
                if (username.equals("deley") && password.equals("123456")) {
                    getSession().setAttribute("UserName", username);
                    setResponsePage(Begin.class);
                } else {
                    loginErrorMessage.setVisible(true);
                }
            }
        };
        add(loginErrorMessage, loginForm);
        loginForm.add(tfUsername, tfPassword);
    }
}
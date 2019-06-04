package com.linkare;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class Search extends BasePage {

    private static final long serialVersionUID = 502327979204314267L;

    public Search() {
        Form<String> searchForm = new Form<String>("searchForm");
        add(searchForm);

        TextField<String> searchName = new TextField<>("searchName", new Model<String>());
        searchForm.add(searchName);
        final WebMarkupContainer containerResults = new WebMarkupContainer("divResults");
        containerResults.setVisible(false);
        containerResults.setOutputMarkupPlaceholderTag(true);
        add(containerResults);

        AjaxButton searchButton = new AjaxButton("searchButton", searchForm) {
            private static final long serialVersionUID = -502382979204357267L;

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                containerResults.setVisible(true);
                target.add(containerResults);
            }
        };
        searchForm.add(searchButton);
    }

}
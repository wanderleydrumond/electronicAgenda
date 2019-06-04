package com.linkare;

import com.linkare.contact.Contact;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

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

        PropertyListView resultList = new PropertyListView<Contact>("contacts", new ListModel<Contact>()){

            @Override
            protected void populateItem(ListItem<Contact> listItem) {
                listItem.add(new Label("name"));
                listItem.add(new Label("email"));
                listItem.add(new Label("telephone"));
                listItem.add(new Label("maritalStatus"));
            }
        };
        containerResults.add(resultList);

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
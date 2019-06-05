package com.linkare;

import com.linkare.contact.Contact;
import com.linkare.contact.ContactDAO;
import org.apache.wicket.markup.html.basic.Label;

import java.sql.Connection;

public class Edit extends Create {

    private static final long serialVersionUID = 502327979204314267L;

    public Edit(Contact contact) {
        super(contact);
        replace(new Label("Title", "Edit a contact"));
    }

    @Override
    protected void save(Contact submitedContact) {
        Connection connection = ((WicketApplication) getApplication()).getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);
        contactDAO.update(submitedContact);
    }
}
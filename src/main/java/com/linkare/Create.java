package com.linkare;

import com.linkare.contact.Contact;
import com.linkare.contact.ContactDAO;
import com.linkare.contact.MaritalStatus;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;

public class Create extends BasePage {

    private static final long serialVersionUID = 502327979204314267L;

    public Create() {
        add(new Label("Title", "Add a contact"));

        Contact contact = new Contact();
        CompoundPropertyModel<Contact> contactCompoundPropertyModel = new CompoundPropertyModel<Contact>(contact);
        Form<Contact> form = new Form<Contact>("contactForm", contactCompoundPropertyModel){
            private static final long serialVersionUID = 502327979204314267L;

            @Override
            protected void onSubmit() {
                Contact submitedContact = getModelObject();
                save(submitedContact);
                setResponsePage(Begin.class);
            }
        };
        add(form);

        TextField<String> inputName = new TextField<String>("name");
        TextField<String> inputEmail = new TextField<String>("email");
        TextField<String> inputTelephone = new TextField<String>("telephone");
        DropDownChoice<MaritalStatus> comboMaritalStatus = new DropDownChoice<>("maritalStatus", asList(MaritalStatus.values()), new IChoiceRenderer<MaritalStatus>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Object getDisplayValue(MaritalStatus maritalStatus) {
                return maritalStatus.getLabel();
            }

            @Override
            public String getIdValue(MaritalStatus maritalStatus, int i) {
                return maritalStatus.name();
            }

            @Override
            public MaritalStatus getObject(String s, IModel<? extends List<? extends MaritalStatus>> iModel) {
                return null;
            }
        });
        form.add(inputName, inputEmail, inputTelephone, comboMaritalStatus);

        inputName.setLabel(Model.of("Contacts Name")).setRequired(true).add(StringValidator.maximumLength(10));
        inputEmail.setLabel(Model.of("Contacts e-mail")).setRequired(true);
        inputEmail.setLabel(Model.of("Contacts e-mail")).add(EmailAddressValidator.getInstance());
        inputTelephone.setLabel(Model.of("Contacts Phone number")).setRequired(true).add(StringValidator.maximumLength(13));

        add(new FeedbackPanel("feedbackMessage" , new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR)));
    }

    private void save(Contact submitedContact) {
//        System.out.println("The following contact will be inserted: " + submitedContact);
        Connection connection = ((WicketApplication) getApplication()).getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);
        contactDAO.insert(submitedContact);
    }
}
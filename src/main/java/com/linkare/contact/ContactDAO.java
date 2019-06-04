package com.linkare.contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private static final String CREATE_SQL = "INSERT INTO contact(username, email, telephone) VALUES (?,?,?)";
    private static final String LIST_SQL = "SELECT id, name, email, telephone, marital_status FROM contact WHERE name LIKE ?";
    private final Connection connection;

    public ContactDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Contact contact) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(CREATE_SQL);
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getEmail());
            preparedStatement.setString(3, contact.getTelephone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Contact> listByName(String name) {
        List<Contact> contacts = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(LIST_SQL);
            preparedStatement.setString(1, name + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Contact contact = new Contact();
                contact.setId(resultSet.getLong(1));
                contact.setName(resultSet.getString(2));
                contact.setEmail(resultSet.getString(3));
                contact.setTelephone(resultSet.getString(4));
                contact.setMaritalStatus(MaritalStatus.valueOf(resultSet.getString(5)));
                contacts.add(contact);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return contacts;
    }
}
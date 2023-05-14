/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import contants.CustomerType;
import dao.CustomerDAO;
import dbmanager.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author HP
 */
public class CustomerDAOImpl implements CustomerDAO {

    private final Connection connection = DBConnection.getConnection();

    @Override
    public boolean addCustomer(Customer customer) {
        boolean success = false;

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO customers (name, email, contact, address, customer_type) "
                + "VALUES (?, ?, ?, ?, ?)"
        )) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getContact());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getCustomerType().toString());

            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        boolean success = false;

        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE customers SET name=?, email=?, contact=?, address=?, customer_type=? "
                + "WHERE customer_id=?"
        )) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getContact());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getCustomerType().toString());
            statement.setInt(6, customer.getCustomerId());

            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        boolean success = false;

        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM customers WHERE customer_id=?"
        )) {
            statement.setInt(1, customerId);

            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public Customer getCustomerById(int customerId) {
        Customer customer = null;

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM customers WHERE customer_id=?"
        )) {
            statement.setInt(1, customerId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("contact"),
                        resultSet.getString("address"),
                        CustomerType.valueOf(resultSet.getString("customer_type"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM customers"
        )) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("contact"),
                        resultSet.getString("address"),
                        CustomerType.valueOf(resultSet.getString("customer_type"))
                );

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}

package com.lumusitech.project.zonafit.data;

import com.lumusitech.project.zonafit.connection.ConnectionCreator;
import com.lumusitech.project.zonafit.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    public static void main(String[] args) {
        ICustomerDAO customerDAO = new CustomerDAO();
        Customer customer = new Customer(4);
        var deleted = customerDAO.delete(customer);
        if (deleted) {
            System.out.println("Customer deleted");
        } else {
            System.out.println("Customer not found");
        }

        var customers = customerDAO.findAll();
        customers.forEach(System.out::println);

    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        PreparedStatement statement;
        ResultSet result;

        try (Connection connection = ConnectionCreator.getConnection()) {
            try {
                var sql = "SELECT * FROM customer ORDER BY id";
                statement = connection.prepareStatement(sql);
                result = statement.executeQuery();
                while (result.next()) {
                    var customer = new Customer();
                    customer.setId(result.getInt("id"));
                    customer.setName(result.getString("name"));
                    customer.setLastname(result.getString("lastname"));
                    customer.setMembership(result.getInt("membership"));
                    customers.add(customer);
                }
            } catch (Exception e) {
                System.out.println("Error fetching customer list");
            }
        } catch (SQLException e) {
            System.out.println("Error at close connection: " + e.getMessage());
        }

        return customers;
    }

    @Override
    public boolean existsById(Customer customer) {
        PreparedStatement statement;
        ResultSet result;

        try (var connection = ConnectionCreator.getConnection()) {
            try {
                var sql = "SELECT * FROM customer WHERE id = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, customer.getId());
                result = statement.executeQuery();
                if (result.next()) {
                    customer.setName(result.getString("name"));
                    customer.setLastname(result.getString("lastname"));
                    customer.setMembership(result.getInt("membership"));
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Error fetching customer by id");
            }
        } catch (SQLException e) {
            System.out.println("Error at close connection: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean save(Customer customer) {
        PreparedStatement statement;
        ResultSet result;
        var sql = "INSERT INTO customer (name, lastname, membership) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionCreator.getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getLastname());
            statement.setInt(3, customer.getMembership());
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error at save customer");
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        PreparedStatement statement;
        ResultSet result;
        var sql = "UPDATE customer SET name = ?, lastname = ?, membership = ? WHERE id = ?";

        try (Connection connection = ConnectionCreator.getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getLastname());
            statement.setInt(3, customer.getMembership());
            statement.setInt(4, customer.getId());
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error at update customer");
        }
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
        PreparedStatement statement;
        ResultSet result;
        var sql = "DELETE FROM customer WHERE id = ?";

        try (Connection connection = ConnectionCreator.getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getId());
            statement.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error at delete customer");
        }
        return false;
    }
}

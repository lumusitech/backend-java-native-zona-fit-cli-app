package com.lumusitech.project.zonafit.data;

import com.lumusitech.project.zonafit.domain.Customer;

import java.util.List;

public interface ICustomerDAO {
    List<Customer> findAll();

    boolean existsById(Customer customer);

    boolean save(Customer customer);

    boolean update(Customer customer);

    boolean delete(Customer customer);
}

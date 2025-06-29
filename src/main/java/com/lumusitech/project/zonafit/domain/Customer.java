package com.lumusitech.project.zonafit.domain;

import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private String lastname;
    private int membership;

    public Customer() {
    }

    public Customer(int id) {
        this.id = id;
    }

    public Customer(String name, String lastname, int membership) {
        this.name = name;
        this.lastname = lastname;
        this.membership = membership;
    }

    public Customer(int id, String name, String lastname, int membership) {
        this(name, lastname, membership);
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", membership=" + membership +
                '}';
    }
}

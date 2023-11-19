package lk.ijse.pos.entity;

public class Customer {
    private String customerId;
    private String name;
    private String address;
    private Double salary;


    public Customer() {
    }

    public Customer(String name, String address, Double salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Customer(String customerId, String name, String address, Double salary) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}

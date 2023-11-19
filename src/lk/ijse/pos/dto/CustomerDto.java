package lk.ijse.pos.dto;

public class CustomerDto {
    private String customerId;
    private String name;
    private String address;
    private Double salary;


    public CustomerDto() {
    }

    public CustomerDto(String name, String address, Double salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public CustomerDto(String customerId, String name, String address, Double salary) {
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

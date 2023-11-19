package lk.ijse.pos.tm;

import javafx.scene.control.Button;

public class CustomerTM {
    private String customerId;
    private String name;
    private String address;
    private Double salary;
    private Button btn;


    public CustomerTM() {
    }

    public CustomerTM(String customerId, String name, String address, Double salary, Button btn) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}

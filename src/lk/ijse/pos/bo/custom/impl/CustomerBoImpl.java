package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBo;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.DaoType;
import lk.ijse.pos.dao.custom.CustomerDao;
import lk.ijse.pos.dto.CustomerDto;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao dao= DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Customer(
                dto.getCustomerId(),dto.getName(),dto.getAddress(),dto.getSalary()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Customer(
                dto.getCustomerId(),dto.getName(),dto.getAddress(),dto.getSalary()
        ));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public ArrayList<CustomerDto> searchCustomer(String searchText) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> arrayList = dao.searchCustomer(searchText);
        ArrayList<CustomerDto> dtos=new ArrayList<>();
        for(Customer c:arrayList){
            dtos.add(new CustomerDto(
                    c.getCustomerId(),c.getName(),c.getAddress(),c.getSalary()
            ));
        }
        return dtos;
    }
}

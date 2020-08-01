package gamma.service.impl;

import gamma.Repository.CustomerRepository;
import gamma.model.entity.Customer;
import gamma.model.entity.Vendor;
import gamma.model.service.CustomerServiceModel;
import gamma.model.service.VendorServiceModel;
import gamma.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerServiceModel addCustomers(CustomerServiceModel customerServiceModel) {
        Customer customer = new Customer();
        customer.setDebit(BigDecimal.valueOf(0));
        customer.setCredit(BigDecimal.valueOf(0));
        customer.setName(customerServiceModel.getName());
        customer.setIdentificationNumber(customerServiceModel.getIdentificationNumber());
        customer.setVatRegistrationNumber(customerServiceModel.getVatRegistrationNumber());
        customer.setType("");
        customer.setBankAccountPartners(customerServiceModel.getBankAccountPartners());
        this.customerRepository.saveAndFlush(customer);
        return customerServiceModel;
    }
}

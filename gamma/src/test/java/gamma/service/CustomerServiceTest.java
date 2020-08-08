package gamma.service;

import gamma.Repository.CustomerRepository;
import gamma.model.entity.Customer;
import gamma.model.service.CustomerServiceModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@SpringBootTest
@Transactional
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    private CustomerServiceModel customerServiceModel;
    private Customer customer;


    @BeforeEach
    public void setUp() {
        
        customer = new Customer();
        customer.setDebit(BigDecimal.valueOf(0));
        customer.setCredit(BigDecimal.valueOf(0));
        customer.setName("pesho");
        customer.setIdentificationNumber("12345");
        customer.setVatRegistrationNumber("BG12345");
        customer.setType("customer");
        customer.setBankAccountPartners("32543632");

        customerServiceModel = new CustomerServiceModel();
        customerServiceModel.setDebit(BigDecimal.valueOf(0));
        customerServiceModel.setCredit(BigDecimal.valueOf(0));
        customerServiceModel.setName("pesho");
        customerServiceModel.setIdentificationNumber("12345");
        customerServiceModel.setVatRegistrationNumber("BG12345");
        customerServiceModel.setType("customer");
        customerServiceModel.setBankAccountPartners("32543632");

    }

    @AfterEach
    public void tearDown() {
        customerRepository.deleteAll();
    }


    @Test
    @Transactional
    void addCustomers() {

        CustomerServiceModel customerServiceModel1 = customerService.addCustomers(customerServiceModel);

        Assertions.assertEquals("pesho", customerServiceModel.getName());

    }
}
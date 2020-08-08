package gamma.service.impl;

import gamma.Repository.ContragentRepository;
import gamma.Repository.CustomerRepository;
import gamma.model.entity.Contragent;
import gamma.model.entity.Customer;
import gamma.model.entity.Vendor;
import gamma.model.service.ContragentServiceModel;
import gamma.service.ContragentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
@Transactional
class ContragentServiceImplTest {

    @Autowired
    ContragentService contragentService;


    @Autowired
    CustomerRepository contragentRepository;


    private ContragentServiceModel contragentServiceModel;
    private Customer contragent;

    @BeforeEach
    public void setUp() {
        contragent = new Customer();
        contragent.setDebit(BigDecimal.valueOf(0));
        contragent.setCredit(BigDecimal.valueOf(0));
        contragent.setName("cus");
        contragent.setIdentificationNumber("12345");
        contragent.setVatRegistrationNumber("BG12345");
        contragent.setType("customer");
        contragent.setBankAccountPartners("32543632");

        contragentServiceModel = new ContragentServiceModel();
        contragentServiceModel.setDebit(BigDecimal.valueOf(0));
        contragentServiceModel.setCredit(BigDecimal.valueOf(0));
        contragentServiceModel.setName("cus");
        contragentServiceModel.setIdentificationNumber("12345");
        contragentServiceModel.setVatRegistrationNumber("BG12345");
        contragentServiceModel.setType("customer");
        contragentServiceModel.setBankAccountPartners("32543632");

        contragentRepository.save(contragent);

    }


    @AfterEach
    public void tearDown() {
        contragentRepository.deleteAll();
    }



    @Test
    @Transactional
    void findAllByType() {

        List<ContragentServiceModel> customerResult = contragentService.findAllByType("customer");

        Assertions.assertEquals("cus", customerResult.get(0).getName());

    }

}
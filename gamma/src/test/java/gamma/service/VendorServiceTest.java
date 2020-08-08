package gamma.service;

import gamma.Repository.VendorRepository;
import gamma.model.entity.Vendor;
import gamma.model.service.VendorServiceModel;
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
class VendorServiceTest {
    @Autowired

    VendorService vendorService;

    @Autowired
    VendorRepository vendorRepository;

    private VendorServiceModel vendorServiceModel;
    private Vendor vendor;


    @BeforeEach
    public void setUp() {
        vendor = new Vendor();
        vendor.setDebit(BigDecimal.valueOf(0));
        vendor.setCredit(BigDecimal.valueOf(0));
        vendor.setName("pesho");
        vendor.setIdentificationNumber("12345");
        vendor.setVatRegistrationNumber("BG12345");
        vendor.setType("customer");
        vendor.setBankAccountPartners("32543632");

        vendorServiceModel = new VendorServiceModel();
        vendorServiceModel.setDebit(BigDecimal.valueOf(0));
        vendorServiceModel.setCredit(BigDecimal.valueOf(0));
        vendorServiceModel.setName("pesho");
        vendorServiceModel.setIdentificationNumber("12345");
        vendorServiceModel.setVatRegistrationNumber("BG12345");
        vendorServiceModel.setType("customer");
        vendorServiceModel.setBankAccountPartners("32543632");

     

    }

    @AfterEach
    public void tearDown() {
        vendorRepository.deleteAll();
    }


    @Test
    @Transactional
    void addVendors() {
        VendorServiceModel vendorServiceModel1 = vendorService.addVendors(this.vendorServiceModel);

        Assertions.assertEquals("pesho", vendorServiceModel1.getName());

    }
}
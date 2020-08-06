package gamma.service;

import gamma.Repository.ContragentRepository;
import gamma.model.entity.Contragent;
import gamma.model.entity.Vendor;
import gamma.model.service.ContragentServiceModel;
import gamma.service.impl.ContragentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContragentServiceTest {

    private ContragentService contragentService;
    private ModelMapper modelMapper;


    @Mock
    ContragentRepository mockContragentRepository;

    @BeforeEach
    public void setUp() {
        contragentService = new ContragentServiceImpl(mockContragentRepository, modelMapper);
    }
    @Test
    void nameTest() {
        Contragent vendor = new Vendor();
        vendor.setDebit(BigDecimal.valueOf(0));
        vendor.setCredit(BigDecimal.valueOf(0));
        vendor.setName("pesho");
        vendor.setIdentificationNumber("12345");
        vendor.setVatRegistrationNumber("BG12345");
        vendor.setType("vendor");
        vendor.setBankAccountPartners("32543632");


        when(mockContragentRepository.contragentsName()).
                thenReturn(List.of(vendor.getName()));

        List<String> name = contragentService.name();

        Assertions.assertEquals(1, name.size());
    }


    @Test
    void customers() {
        Contragent customer = new Vendor();
        customer.setDebit(BigDecimal.valueOf(0));
        customer.setCredit(BigDecimal.valueOf(0));
        customer.setName("pesho");
        customer.setIdentificationNumber("12345");
        customer.setVatRegistrationNumber("BG12345");
        customer.setType("customer");
        customer.setBankAccountPartners("32543632");


        when(mockContragentRepository.customer()).
                thenReturn(List.of(customer.getName()));

        List<String> name = contragentService.customers();

        Assertions.assertEquals(1, name.size());
    }

    @Test
    void vendorsTest() {
        Contragent vendor = new Vendor();
        vendor.setDebit(BigDecimal.valueOf(0));
        vendor.setCredit(BigDecimal.valueOf(0));
        vendor.setName("pesho");
        vendor.setIdentificationNumber("12345");
        vendor.setVatRegistrationNumber("BG12345");
        vendor.setType("vendor");
        vendor.setBankAccountPartners("32543632");


        when(mockContragentRepository.vendor()).
                thenReturn(List.of(vendor.getName()));

        List<String> name = contragentService.vendors();

        Assertions.assertEquals(1, name.size());

    }
}
package gamma.service.impl;

import gamma.Repository.ContragentRepository;
import gamma.Repository.VendorRepository;
import gamma.model.entity.Vendor;
import gamma.model.service.VendorServiceModel;
import gamma.service.VendorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final ModelMapper modelMapper;
    private final ContragentRepository contragentRepository;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(ModelMapper modelMapper, ContragentRepository contragentRepository, VendorRepository vendorRepository) {
        this.modelMapper = modelMapper;
        this.contragentRepository = contragentRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public String importVendors() throws FileNotFoundException {
        return null;
    }

    @Override
    public VendorServiceModel addVendors(VendorServiceModel vendorServiceModel) {
        Vendor vendor = new Vendor();
        vendor.setDebit(BigDecimal.valueOf(0));
        vendor.setCredit(BigDecimal.valueOf(0));
        vendor.setName(vendorServiceModel.getName());
        vendor.setIdentificationNumber(vendorServiceModel.getIdentificationNumber());
        vendor.setVatRegistrationNumber(vendorServiceModel.getVatRegistrationNumber());
        vendor.setType("");
        vendor.setBankAccountPartners(vendorServiceModel.getBankAccountPartners());
        this.vendorRepository.saveAndFlush(vendor);
        return vendorServiceModel;
    }
}

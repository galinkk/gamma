package gamma.service;


import gamma.model.service.VendorServiceModel;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public interface VendorService {
    String importVendors() throws FileNotFoundException;

    VendorServiceModel addVendors(VendorServiceModel vendorServiceModel);

}

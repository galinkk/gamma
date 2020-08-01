package gamma.model.binding;

import com.sun.xml.bind.v2.TODO;
import gamma.model.service.LedgerEntityServiceModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class VendorsAddBindingModel {
    private String name;
    private String identificationNumber;
    private String vatRegistrationNumber;
    private String type;
    private String bankAccountPartners;

    public VendorsAddBindingModel() {
    }

    @Length(min = 2 , max = 10,message = "Name must be between 2 and 10 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 2 , max = 10,message = "Identification number must be between 2 and 10 characters")
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
    @Length(min = 2 , max = 10,message = "VAT registration number must be between 2 and 10 characters")
    public String getVatRegistrationNumber() {
        return vatRegistrationNumber;
    }

    public void setVatRegistrationNumber(String vatRegistrationNumber) {
        this.vatRegistrationNumber = vatRegistrationNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBankAccountPartners() {
        return bankAccountPartners;
    }

    public void setBankAccountPartners(String bankAccountPartners) {
        this.bankAccountPartners = bankAccountPartners;
    }
}

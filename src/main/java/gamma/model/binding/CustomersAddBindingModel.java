package gamma.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CustomersAddBindingModel {
    private String name;
    private String identificationNumber;
    private String vatRegistrationNumber;
    private String type;
    private String bankAccountPartners;

    public CustomersAddBindingModel() {
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

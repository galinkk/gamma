package gamma.model.view;

import java.math.BigDecimal;


public class ContragentViewModel {
    private String name;
    private String identificationNumber;
    private String vatRegistrationNumber;
    private String bankAccountPartners;
    private BigDecimal Debit;
    private BigDecimal Credit;
    private BigDecimal balance;


    public ContragentViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getVatRegistrationNumber() {
        return vatRegistrationNumber;
    }

    public void setVatRegistrationNumber(String vatRegistrationNumber) {
        this.vatRegistrationNumber = vatRegistrationNumber;
    }

    public String getBankAccountPartners() {
        return bankAccountPartners;
    }

    public void setBankAccountPartners(String bankAccountPartners) {
        this.bankAccountPartners = bankAccountPartners;
    }

    public BigDecimal getDebit() {
        return Debit;
    }

    public void setDebit(BigDecimal debit) {
        Debit = debit;
    }

    public BigDecimal getCredit() {
        return Credit;
    }

    public void setCredit(BigDecimal credit) {
        Credit = credit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}

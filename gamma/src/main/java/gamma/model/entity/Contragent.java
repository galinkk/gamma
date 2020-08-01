package gamma.model.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "contragents")
public abstract class Contragent extends BaseEntity{


    private String name;
    private String identificationNumber;
    private String vatRegistrationNumber;
    private String bankAccountPartners;
    private BigDecimal Debit;
    private BigDecimal Credit;
    private Set<LedgerEntity> ledgerEntity;
    private String type;

    public Contragent() {
    }

    @Column(name = "type",nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "identification_number",nullable = false)
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    @Column(name = "vat_registration_number",nullable = false)
    public String getVatRegistrationNumber() {
        return vatRegistrationNumber;
    }

    public void setVatRegistrationNumber(String vatRegistrationNumber) {
        this.vatRegistrationNumber = vatRegistrationNumber;
    }

    @Column(name = "debit")
    public BigDecimal getDebit() {
        return Debit;
    }

    public void setDebit(BigDecimal debit) {
        Debit = debit;
    }

    @Column(name = "credit")
    public BigDecimal getCredit() {
        return Credit;
    }

    public void setCredit(BigDecimal credit) {
        Credit = credit;
    }

    @Column(name = "bank_account")
    public String getBankAccountPartners() {
        return bankAccountPartners;
    }

    public void setBankAccountPartners(String bankAccountPartners) {
        this.bankAccountPartners = bankAccountPartners;
    }

    @OneToMany(mappedBy = "contragent", fetch = FetchType.LAZY)
    public Set<LedgerEntity> getLedgerEntity() {
        return ledgerEntity;
    }

    public void setLedgerEntity(Set<LedgerEntity> ledgerEntity) {
        this.ledgerEntity = ledgerEntity;
    }

}

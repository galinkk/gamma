package gamma.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    private String accountNumber;
    private String name;
    private BigDecimal Debit;
    private BigDecimal Credit;
    private String balanceIncome;
    private String activePassive;
    private Set<LedgerEntity> ledgerEntities;

    public Account() {
    }

    @Column(name = "account_number",nullable = false,unique = true)
    @Length(min = 3,max = 3,message = "AccountNumber length must be three characters")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "name",nullable = false,unique = true)
    @Length(min = 1,max = 50,message = "Name length must be between one and fifty characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Column(name = "balance_income")
    public String getBalanceIncome() {
        return balanceIncome;
    }

    public void setBalanceIncome(String balanceIncome) {
        this.balanceIncome = balanceIncome;
    }

    @Column(name = "active_passive")
    public String getActivePassive() {
        return activePassive;
    }

    public void setActivePassive(String activePassive) {
        this.activePassive = activePassive;
    }

    @ManyToMany(mappedBy = "accounts")
    public Set<LedgerEntity> getLedgerEntities() {
        return ledgerEntities;
    }

    public void setLedgerEntities(Set<LedgerEntity> ledgerEntities) {
        this.ledgerEntities = ledgerEntities;
    }
}

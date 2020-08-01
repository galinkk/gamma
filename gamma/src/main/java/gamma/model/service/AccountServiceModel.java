package gamma.model.service;

import java.math.BigDecimal;


public class AccountServiceModel extends BaseServiceModel {
    private String accountNumber;
    private String name;
    private BigDecimal Debit;
    private BigDecimal Credit;
    private BigDecimal balance;
    private String balanceIncome;
    private String activePassive;



    public AccountServiceModel() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBalanceIncome() {
        return balanceIncome;
    }

    public void setBalanceIncome(String balanceIncome) {
        this.balanceIncome = balanceIncome;
    }

    public String getActivePassive() {
        return activePassive;
    }

    public void setActivePassive(String activePassive) {
        this.activePassive = activePassive;
    }
}

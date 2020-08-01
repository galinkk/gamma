package gamma.model.binding;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class AccountsAddBindingModel {

    private String accountNumber;
    private String name;
    private String balanceIncome;
    private String activePassive;


    public AccountsAddBindingModel() {
    }

    @Length(min = 3,max = 3,message = "AccountNumber length must be three characters")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Length(min = 1,max = 50,message = "Name length must be between one and fifty characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

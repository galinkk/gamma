package gamma.model.service;

import gamma.model.entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class LedgerEntityServiceModel extends BaseServiceModel{
    private LocalDateTime postingDate;
    private List<AccountServiceModel> accounts;
    private Contragent contragent;
    private Integer documentNumber;
    private LocalDateTime documentDate;
    private String description;
    private BigDecimal debit;
    private BigDecimal credit;
    private BigDecimal amount;
    private User user;
    private boolean payed;


    public LedgerEntityServiceModel() {
    }

    public LocalDateTime getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDateTime postingDate) {
        this.postingDate = postingDate;
    }

    public List<AccountServiceModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountServiceModel> accounts) {
        this.accounts = accounts;
    }

    public Contragent getContragent() {
        return contragent;
    }

    public void setContragent(Contragent contragent) {
        this.contragent = contragent;
    }

    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    public LocalDateTime getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDateTime documentDate) {
        this.documentDate = documentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

}

package gamma.model.service;

import gamma.model.entity.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class LedgerEntityyServiceModel {

    private LocalDateTime postingDate;
    private String documentType;
    private List<Account> accounts;
    private Contragent contragent;
    private Integer documentNumber;
    private Date documentDate;
    private List<Description> description;
    private List<Debit> debit;
    private List<Credit> credit;
    private User user;
    private boolean payed;

    public LedgerEntityyServiceModel() {
    }

    public LocalDateTime getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDateTime postingDate) {
        this.postingDate = postingDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
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

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public List<Description> getDescription() {
        return description;
    }

    public void setDescription(List<Description> description) {
        this.description = description;
    }

    public List<Debit> getDebit() {
        return debit;
    }

    public void setDebit(List<Debit> debit) {
        this.debit = debit;
    }

    public List<Credit> getCredit() {
        return credit;
    }

    public void setCredit(List<Credit> credit) {
        this.credit = credit;
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

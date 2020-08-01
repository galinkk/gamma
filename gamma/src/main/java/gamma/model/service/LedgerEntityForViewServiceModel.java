package gamma.model.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class LedgerEntityForViewServiceModel {
    private LocalDateTime postingDate;
    private String documentType;
    private List<String> accounts;
    private String contragent;
    private Integer documentNumber;
    private Date documentDate;
    private List<String> description;
    private List<BigDecimal> debit;
    private List<BigDecimal> credit;
    private String user;
    private String id;
    private boolean payed;

    public LedgerEntityForViewServiceModel() {
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

    public List<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContragent() {
        return contragent;
    }

    public void setContragent(String contragent) {
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

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<BigDecimal> getDebit() {
        return debit;
    }

    public void setDebit(List<BigDecimal> debit) {
        this.debit = debit;
    }

    public List<BigDecimal> getCredit() {
        return credit;
    }

    public void setCredit(List<BigDecimal> credit) {
        this.credit = credit;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}

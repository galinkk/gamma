package gamma.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ledgerEntities")
public class LedgerEntity extends BaseEntity{

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

    public LedgerEntity() {
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    public List<Credit> getCredit() {
        return credit;
    }

    public void setCredit(List<Credit> credit) {
        this.credit = credit;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    public List<Debit> getDebit() {
        return debit;
    }

    public void setDebit(List<Debit> debit) {
        this.debit = debit;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    public List<Description> getDescription() {
        return description;
    }

    public void setDescription(List<Description> description) {
        this.description = description;
    }

    @Column(name = "posting_date",nullable = false)
    public LocalDateTime getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDateTime postingDate) {
        this.postingDate = postingDate;
    }

    @NotNull
    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @ManyToOne
    public Contragent getContragent() {
        return contragent;
    }

    public void setContragent(Contragent contragent) {
        this.contragent = contragent;
    }
    @Column(name = "document_number",nullable = false)
    @Min(value = 1)
    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Column(name = "document_date",nullable = false)
    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    @ManyToOne
    @NotNull
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Column(name = "is_payed", nullable = false)
    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

}

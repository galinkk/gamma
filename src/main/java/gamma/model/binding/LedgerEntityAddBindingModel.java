package gamma.model.binding;

import gamma.model.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class  LedgerEntityAddBindingModel {

    private LocalDateTime postingDate;
    private String documentType;
    private LinkedList<String> accounts;
    private Integer documentNumber;
    private Date documentDate;
    private LinkedList<String> description;
    private LinkedList<BigDecimal> debit;
    private LinkedList<BigDecimal> credit;
    private String user;
    private String ids;
    private boolean payed;
    private String contragents;

    public LedgerEntityAddBindingModel() {
    }
    @NotNull
    public String getContragents() {
        return contragents;
    }

    public void setContragents(String contragents) {
        this.contragents = contragents;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
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

    @NotNull
    @Min(value = 1)
    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }


    @NotNull
    public List<String> getDescription() {
        return description;
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

    @NotNull
    public LinkedList<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(LinkedList<String> accounts) {
        this.accounts = accounts;
    }

    public void setDescription(LinkedList<String> description) {
        this.description = description;
    }

    @NotNull
    public LinkedList<BigDecimal> getDebit() {
        return debit;
    }

    public void setDebit(LinkedList<BigDecimal> debit) {
        this.debit = debit;
    }

    @NotNull
    public LinkedList<BigDecimal> getCredit() {
        return credit;
    }

    public void setCredit(LinkedList<BigDecimal> credit) {
        this.credit = credit;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}

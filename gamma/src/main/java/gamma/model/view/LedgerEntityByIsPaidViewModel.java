package gamma.model.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class LedgerEntityByIsPaidViewModel {
    private String contragent;
    private Integer documentNumber;
    private Date documentDate;
    private String id;
    private List<String> description;
    private List<BigDecimal> debit;
    private List<BigDecimal> credit;
    private boolean payed;

    public LedgerEntityByIsPaidViewModel() {
    }

    public String getContragent() {
        return contragent;
    }


    public void setContragent(String contragent) {
        this.contragent = contragent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}

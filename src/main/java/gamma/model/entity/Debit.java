package gamma.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "debits")
public class Debit extends BaseEntity{
    private BigDecimal debit;

    public Debit(BigDecimal debit) {
        this.debit = debit;
    }

    public Debit() {
    }

    @Column(name = "debit")
    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }
}


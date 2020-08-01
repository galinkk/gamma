package gamma.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "credits")
public class Credit extends BaseEntity{
    private BigDecimal credit;

    public Credit(BigDecimal credit) {
        this.credit = credit;
    }

    public Credit() {
    }

    @Column(name = "credit")
    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }
}

package gamma.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vendors")
public class Vendor extends Contragent {
    private String type;

    public Vendor() {
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        type = "vendors";
        this.type = type;
    }
}

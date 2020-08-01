package gamma.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends Contragent{
    private String type;

    public Customer() {
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        type = "customer";
        this.type = type;
    }



}

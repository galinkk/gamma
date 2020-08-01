package gamma.model.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "descriptions")
public class Description extends BaseEntity{
    private String description;

    public Description() {
    }
    public Description(String description) {
        this.description=description;

    }

    @Column(name = "descripton")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

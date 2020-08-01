package gamma.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class LedgerEntityApprovedBindingModel {

    private String ids;

    public LedgerEntityApprovedBindingModel() {
    }

    @Length(min = 1)
    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}

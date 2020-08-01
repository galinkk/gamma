package gamma.service;

import gamma.model.binding.LedgerEntityAddBindingModel;
import gamma.model.service.LedgerEntityAddServiceModel;
import gamma.model.service.LedgerEntityForViewServiceModel;
import gamma.model.service.LedgerEntityServiceModel;
import gamma.model.service.LedgerEntityyServiceModel;
import java.util.*;

import java.util.Arrays;

public interface LedgerEntityService {
    LedgerEntityAddServiceModel postLedgerEntity(LedgerEntityAddServiceModel ledgerEntityAddServiceModel, boolean isInvoice);

    List<LedgerEntityForViewServiceModel> findAll();

    List<LedgerEntityForViewServiceModel> findAllByPayed();

    void isApproved(String username);

    public boolean existsUser(String ids);
}

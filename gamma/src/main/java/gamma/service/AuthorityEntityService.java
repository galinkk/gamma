package gamma.service;

import gamma.model.service.AuthorityEntityServiceModel;

public interface AuthorityEntityService {
    AuthorityEntityServiceModel findByName(String name);
}

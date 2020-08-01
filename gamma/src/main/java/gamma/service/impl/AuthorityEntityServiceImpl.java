package gamma.service.impl;

import gamma.Repository.AuthorityEntityRepository;
import gamma.model.entity.AuthorityEntity;
import gamma.model.entity.Role;
import gamma.model.service.AuthorityEntityServiceModel;
import gamma.model.service.UserServiceModel;
import gamma.service.AuthorityEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.*;

import javax.annotation.PostConstruct;

@Service
public class AuthorityEntityServiceImpl implements AuthorityEntityService {
    private final AuthorityEntityRepository authorityEntityRepository;
    private final ModelMapper modelMapper;

    public AuthorityEntityServiceImpl(AuthorityEntityRepository authorityEntityRepository, ModelMapper modelMapper) {
        this.authorityEntityRepository = authorityEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorityEntityServiceModel findByName(String name) {
        return this.authorityEntityRepository.findByName(name).map(auth -> this.modelMapper.map(auth,AuthorityEntityServiceModel.class))
                .orElse(null);
        }
}

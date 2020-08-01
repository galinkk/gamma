package gamma.service.impl;

import gamma.Repository.ContragentRepository;
import gamma.model.entity.Contragent;
import gamma.model.service.ContragentServiceModel;
import gamma.service.ContragentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContragentServiceImpl implements ContragentService {

    private final ContragentRepository contragentRepository;
    private final ModelMapper modelMapper;

    public ContragentServiceImpl(ContragentRepository contragentRepository, ModelMapper modelMapper) {
        this.contragentRepository = contragentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ContragentServiceModel> findAllByType(String type) {
        List<Contragent> allVendorsEntity = this.contragentRepository.findByType(type);
        List<ContragentServiceModel> allVendors = new ArrayList<>();
        for (int i = 0; i < allVendorsEntity.size(); i++) {
            Contragent contragent = allVendorsEntity.get(i);
            ContragentServiceModel map = modelMapper.map(contragent, ContragentServiceModel.class);
            map.setBalance(contragent.getDebit().subtract(contragent.getCredit()));
            allVendors.add(map);
        }
        return allVendors;
    }

    @Override
    public List<String> name() {
        List<String> names = this.contragentRepository.contragentsName();
        return names;
    }

    @Override
    public List<String> customers() {
        return this.contragentRepository.customer();
    }

    @Override
    public List<String> vendors() {
        return this.contragentRepository.vendor();
    }

}

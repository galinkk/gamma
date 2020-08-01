package gamma.service;

import gamma.model.service.ContragentServiceModel;


import java.util.List;

public interface ContragentService {

    List<ContragentServiceModel> findAllByType(String type);
    List<String> name();
    List<String> customers();
    List<String> vendors();

 }

package gamma.service.impl;

import gamma.model.entity.Role;
import gamma.service.RoleService;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public List<Role> allRoles() {
      return Arrays.stream(Role.values()).collect(Collectors.toList());
    }
}

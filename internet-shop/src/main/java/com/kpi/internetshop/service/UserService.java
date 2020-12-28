package com.kpi.internetshop.service;

import com.kpi.internetshop.entity.Role;
import com.kpi.internetshop.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends GenericService<User, Long>, UserDetailsService {
    User create(User item);
    boolean isUniqueUsername(String email);
    List<User> getAllByRole(Role role);
    User getByEmail(String email);
}

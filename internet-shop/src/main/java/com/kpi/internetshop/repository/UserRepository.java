package com.kpi.internetshop.repository;

import com.kpi.internetshop.entity.Role;
import com.kpi.internetshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> getByRoles(Role role);
}

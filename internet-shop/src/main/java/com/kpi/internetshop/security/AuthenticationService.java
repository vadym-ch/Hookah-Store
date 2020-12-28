package com.kpi.internetshop.security;

import com.kpi.internetshop.entity.User;

public interface AuthenticationService {
    User register(String firstName, String lastName, String email, String password);
}

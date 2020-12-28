package com.kpi.internetshop.service.impl;

import com.kpi.internetshop.entity.Role;
import com.kpi.internetshop.entity.User;
import com.kpi.internetshop.repository.ShoppingCartRepository;
import com.kpi.internetshop.repository.UserRepository;
import com.kpi.internetshop.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ShoppingCartRepository shoppingCartRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User item) {
        item.setPassword(passwordEncoder.encode(item.getPassword()));
        return userRepository.save(item);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User item) {
        User user = get(item.getId());
        user.setRoles(item.getRoles());
        user.setEmail(item.getEmail());
        user.setFirstName(item.getFirstName());
        user.setLastName(item.getLastName());
        user.setPassword(item.getPassword());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        shoppingCartRepository.deleteByUser(get(id));
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toString())).collect(Collectors.toSet());
    }

    public boolean isUniqueUsername(String email) {
        return (userRepository.findByEmail(email) == null) ? true : false;
    }

    @Override
    public List<User> getAllByRole(Role role) {
        return userRepository.getByRoles(role);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

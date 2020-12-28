package com.kpi.internetshop.mapper;

import com.kpi.internetshop.entity.User;
import com.kpi.internetshop.entity.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapFromUserToUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}

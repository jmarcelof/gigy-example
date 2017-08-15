package com.poc.DTO;

import com.google.common.collect.Iterables;
import com.poc.model.User;

public class UserInfoDTO {

    private User user;

    public UserInfoDTO(User user) {
        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getAuthority() {
        return Iterables.get(user.getAuthorities(), 0).getAuthority();
    }
}

package com.experiment.backend.service.impl.utils;

import com.experiment.backend.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 实现该接口,自己定义返回的结果
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 账户没有黑名单
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 授权没有过期
        return true;
    }

    @Override
    public boolean isEnabled() { //用户是否启用
        return true;
    }
}

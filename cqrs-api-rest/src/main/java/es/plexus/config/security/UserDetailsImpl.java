package es.plexus.config.security;

import es.plexus.entity.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRol = user.getUserRol().getName();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userRol));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }
    public Long getId() {
        return user.getId();
    }

    public String getSurnames() {
        return user.getSurnames();
    }
    public String getName() {
        return user.getName();
    }
    public String getRolName() {
        return user.getUserRol().getName();
    }
}

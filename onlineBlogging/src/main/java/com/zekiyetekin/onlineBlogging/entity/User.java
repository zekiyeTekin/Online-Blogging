package com.zekiyetekin.onlineBlogging.entity;

import com.zekiyetekin.onlineBlogging.enumuration.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String mail;

    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String roleName = role.getName().getRoleName();
        //if (roleName == null) {
        // throw new NullPointerException("User or Role name is null"); }

        //return List.of(new SimpleGrantedAuthority(roleName));

        if (role.getName() == RoleEnum.USER) {
            return List.of(new SimpleGrantedAuthority("USER"));
        } else if (role.getName() == RoleEnum.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ADMIN"));
        } else {
            throw new IllegalArgumentException("Invalid role: " + roleName);
        }
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
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
}

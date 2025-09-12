package com.valeshop.timesheet.entities.user;

import com.valeshop.timesheet.entities.demands.DemandRecord;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tb_users")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable, UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String email;
    protected String password;
    protected Integer userType;

    @OneToMany(mappedBy = "user")
    private final Set<DemandRecord> demand = new HashSet<>();

    public User(Long id, String email, String password, UserType userType) {
        this.id = id;
        this.email = email;
        this.password = password;
        setUserType(userType);
    }

    public UserType getUserType() {
        return UserType.valueOf(userType);
    }

    public void setUserType(UserType userType) {
        if (userType != null) this.userType = userType.getCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userType == UserType.Administrador.getCode()) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"), new SimpleGrantedAuthority("ROLE_NORMAL"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_NORMAL"));
        }
    }

    @Override
    public String getUsername() {
        return email;
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

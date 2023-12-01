package rocha.andre.project.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rocha.andre.project.domain.user.DTO.Role;
import rocha.andre.project.domain.user.DTO.UserDTO;
import rocha.andre.project.domain.user.DTO.UserUpdateDTO;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String senha;
    private int matricula;
    @Enumerated(EnumType.STRING)
    private Role tipo;

    private double saldo;


    public User (UserDTO data, int matricula) {
        this.login = data.login();
        this.tipo = data.tipo();
        this.matricula = matricula;
        this.saldo = 0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.tipo == Role.BIBLIOTECARIO) {
            return List.of(new SimpleGrantedAuthority("ROLE_BIBLIOTECARIO"),new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
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

    public void setPassword(String encodedPassword) {
        this.senha = encodedPassword;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public void updateUser(UserUpdateDTO data) {
        if (data.login() != null) {
            this.login = data.login();
        }

        if (data.tipo() != null) {
            this.tipo = data.tipo();
        }

    }
}

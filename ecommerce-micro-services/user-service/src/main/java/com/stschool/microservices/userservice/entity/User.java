package stschool.microservices.userservice.entity;

import lombok.Data;
import stschool.microservices.userservice.enums.Gender;
import stschool.microservices.userservice.enums.Role;
import stschool.microservices.userservice.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="users")

public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoggedIn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.getName();
    }
}

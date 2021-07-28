package iClinic.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity(name = "User")
@Table(name = "users")
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    @Column(name = "fio")
    private String fio;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;



//    @Column(name = "role_id",updatable = false)
//    private int roleId;
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    private Role role;

//    @Column(name = "specialisation_id", updatable = false)
//    private Integer specialisation_id;
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Specialisation.class)
    @JoinColumn(name = "specialisation_id")
    private Specialisation specialisation;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


//    public int getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(int roleId) {
//        this.roleId = roleId;
//    }
//
//    public Integer getSpecialsationId() {
//        return specialsationId;
//    }
//
//    public void setSpecialsationId(Integer specialsationId) {
//        this.specialsationId = specialsationId;
//    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(Specialisation specialsation) {
        this.specialisation = specialsation;
    }

    @Override
    public String toString() {
        return fio;
    }
}

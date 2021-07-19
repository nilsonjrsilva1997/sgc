package br.com.sgc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String name;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnoreProperties(value = {"user"})
    @JsonManagedReference
    private List<Customer> customer;

    public User() {
    }

    public User(String email) {
        super();
        this.email = email;
    }
    public User(User user) {
        super();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.id = user.getId();
    }
    public User(String email, String password, List<Role> roles) {
        super();
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public User(String name, String email, String password, List<Role> roles) {
        super();
        this.email = email;
        this.name = name;
        this.roles = roles;
        this.password = password;
    }
}
package br.com.sgc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String fullName;

    @Column(unique = true)
    private String cpfCnpj;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnoreProperties(value = {"customer"})
    @JsonManagedReference
    private List<Phone> phones;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnoreProperties(value = {"customer"})
    @JsonManagedReference
    private List<Address> addresses;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
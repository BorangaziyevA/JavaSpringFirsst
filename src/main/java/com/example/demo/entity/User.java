package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="usr")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
 @Column
 private String gender;
 @Column
 private int age;


    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany
    private List<Product> products;

    public User(final  String name, final String email, final String password)
    {
        this.name=name;
        this.email=email;
        this.password=password;
        this.age=age;
        this.gender=gender;
    }


}


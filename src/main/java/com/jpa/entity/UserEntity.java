package com.jpa.entity;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column (name = "firstname")
    private String firstName ;

    @Column (name = "lastname")
    private  String lastName ;

    @Column (name =  "email")
    private  String email ;

    // Constructor với các tham số
    public UserEntity(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserEntity() {
    }
}

package com.Shop.Shop.model;


import lombok.Data;

import javax.persistence.*;

@SuppressWarnings( "ALL" )
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(length = 20)
    private String name;
    @Column(length = 20)
    private String surname;
    @Column(length = 64)
    private String password;
    @Column(name = "phoneNumber",unique = true)
    public Long phoneNumber;
    @Column(length = 64,unique = true,name = "email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}

package com.mum.ecommerce.admin;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Account {

    private String username;

    private String password;

    private boolean status;

    private String email;

    private String firstName;

    private String lastName;

    private boolean deleted;


    public Account(String username,String password,String email,String firstName,String lastName){
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.deleted = Boolean.FALSE;
    }

}

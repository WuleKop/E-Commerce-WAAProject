package edu.mum.cs.clientservice.adminmodel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {


    private Long id;


    private String email;


    private String password;


    private String name;


    private String lastName;


    private int active;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
//    private Set<Role> roles;

    private Address address;

    private Role role;

    private Status status;
}

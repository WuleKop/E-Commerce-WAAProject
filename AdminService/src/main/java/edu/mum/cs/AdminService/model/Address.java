package edu.mum.cs.AdminService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String country;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @NotEmpty
    private String street;

}

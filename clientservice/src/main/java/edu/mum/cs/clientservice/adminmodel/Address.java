package edu.mum.cs.clientservice.adminmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class Address implements Serializable {


    private Long id;


    private String country;


    private String city;


    private String state;


    private String street;

}

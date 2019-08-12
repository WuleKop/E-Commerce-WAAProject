package edu.mum.cs.sellerService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Address {

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
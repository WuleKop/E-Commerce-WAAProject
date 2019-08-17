package edu.mum.cs.AdminService.iservice;

import edu.mum.cs.AdminService.model.Address;

import java.util.List;

public interface AddressService {

    Address saveAddress(Address address);

    Address findOneAddress(Long id);

    List<Address> findAllAddresses();

    void delete(Address address);
}

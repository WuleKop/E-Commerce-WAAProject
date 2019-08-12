package edu.mum.cs.AdminService.serviceImpl;

import edu.mum.cs.AdminService.iservice.AddressService;
import edu.mum.cs.AdminService.model.Address;
import edu.mum.cs.AdminService.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address findOneAddress(Long id) {
        return addressRepository.findById(id).get();
    }

    @Override
    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }
}

package edu.mum.cs.AdminService.iservice;

import edu.mum.cs.AdminService.model.Advertissement;

import java.util.List;

public interface AdvertissementService {
    Advertissement save(Advertissement advertissement);
    Advertissement findById(Long id);
    Advertissement update(Advertissement advertissement);
    void delete(Long id);
    List<Advertissement> getAllAdvertissements();
}

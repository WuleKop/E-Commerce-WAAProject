package edu.mum.cs.AdminService.serviceImpl;

import edu.mum.cs.AdminService.iservice.AdvertissementService;
import edu.mum.cs.AdminService.model.Advertissement;
import edu.mum.cs.AdminService.repository.AdvertissementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdvertissementServiceImpl implements AdvertissementService {

    @Autowired
   AdvertissementRepository advertissementRepository;
    @Override
    public Advertissement save(Advertissement advertissement) {
        return advertissementRepository.save(advertissement);
    }

    @Override
    public Advertissement findById(Long id) {
        return advertissementRepository.findById(id).get();
    }

    @Override
    public Advertissement update(Advertissement advertissement) {
        return advertissementRepository.save(advertissement);
    }

    @Override
    public void delete(Long id) {
        advertissementRepository.deleteById(id);
    }

    @Override
    public List<Advertissement> getAllAdvertissements() {
        return advertissementRepository.findAll() ;
    }
}

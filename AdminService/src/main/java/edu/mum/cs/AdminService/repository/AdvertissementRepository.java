package edu.mum.cs.AdminService.repository;

import edu.mum.cs.AdminService.model.Advertissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertissementRepository extends JpaRepository<Advertissement,Long> {
}

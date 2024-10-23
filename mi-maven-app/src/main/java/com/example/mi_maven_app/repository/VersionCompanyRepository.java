package com.example.mi_maven_app.repository;

import com.example.mi_maven_app.entity.VersionCompany;
import com.example.mi_maven_app.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VersionCompanyRepository extends JpaRepository<VersionCompany, Long> {
    
    // Método personalizado para buscar VersionCompany por Company
    Optional<VersionCompany> findByCompany(Company company);
}

package com.example.mi_maven_app.controller;

import com.example.mi_maven_app.entity.Company;
import com.example.mi_maven_app.entity.VersionCompany;
import com.example.mi_maven_app.repository.CompanyRepository;
import com.example.mi_maven_app.repository.VersionCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private VersionCompanyRepository versionCompanyRepository;

    // Métodos CRUD...

    // Método GET adicional
    @GetMapping("/detalle/{codigoCompany}")
    public ResponseEntity<Map<String, Object>> getCompanyDetails(@PathVariable String codigoCompany) {
        Optional<Company> companyOptional = companyRepository.findByCodigoCompany(codigoCompany);
        if (!companyOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Company company = companyOptional.get();
        Optional<VersionCompany> versionCompanyOptional = versionCompanyRepository.findByCompany(company);

        if (!versionCompanyOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        VersionCompany versionCompany = versionCompanyOptional.get();
        String appName = versionCompany.getVersion().getApplication().getAppName();
        String version = versionCompany.getVersion().getVersion();

        Map<String, Object> response = new HashMap<>();
        response.put("codigo_company", company.getCodigoCompany());
        response.put("name_company", company.getNameCompany());
        response.put("app_name", appName);
        response.put("version", version);

        return ResponseEntity.ok(response);
    }
}

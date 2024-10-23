package com.example.mi_maven_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "version_company")
@Data
public class VersionCompany {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version_company_id")
    private Long versionCompanyId;

    @OneToOne
    @JoinColumn(name = "company_id", unique = true)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "version_id")
    private Version version;

    @Column(name = "version_company_description")
    private String versionCompanyDescription;
}

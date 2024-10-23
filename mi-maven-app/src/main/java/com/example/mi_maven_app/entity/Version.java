package com.example.mi_maven_app.entity;

import jakarta.persistence.*;
    import lombok.Data;

@Entity
@Table(name = "version")
@Data
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version_id")
    private Long versionId;

    @OneToOne
    @JoinColumn(name = "app_id", unique = true)
    private Application application;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "version_description")
    private String versionDescription;
}

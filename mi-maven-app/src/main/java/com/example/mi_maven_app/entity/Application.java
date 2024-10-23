package com.example.mi_maven_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "application")
@Data  // Lombok genera automáticamente los getters, setters y otros métodos comunes
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private Long appId;

    @Column(name = "app_code", unique = true, nullable = false)
    private String appCode;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "app_description")
    private String appDescription;
}

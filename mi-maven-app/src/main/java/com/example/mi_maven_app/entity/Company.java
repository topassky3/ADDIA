package com.example.mi_maven_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "company")
@Data // Asegúrate de que Lombok esté funcionando
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Long idCompany;

    @Column(name = "codigo_company", unique = true, nullable = false)
    private String codigoCompany;

    @Column(name = "name_company")
    private String nameCompany;

    @Column(name = "description_company")
    private String descriptionCompany;

    // Si Lombok no funciona, agrega manualmente los getters y setters
}

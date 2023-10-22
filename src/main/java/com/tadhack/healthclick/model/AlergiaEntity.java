package com.tadhack.healthclick.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alergia")
@Getter
@Setter
@NoArgsConstructor
public class AlergiaEntity {
    @Id
    @Column(nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_alergia;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String grado_severidad;

    @Column(nullable = false, length = 100)
    private String otros_detalles;

    @ManyToOne
    @JoinColumn(name="paciente_cedula")
    @JsonBackReference
    private PacienteEntity paciente_cedula;
}

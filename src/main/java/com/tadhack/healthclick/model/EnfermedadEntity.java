package com.tadhack.healthclick.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enfermedad")
@Getter
@Setter
@NoArgsConstructor
public class EnfermedadEntity {
    @Id
    @Column(length = 20)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_enfermedad;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String fecha_diagnostico;

    @Column(nullable = false, length = 50)
    private String estado_actual;

    @Column(nullable = false, length = 100)
    private String otros_detalles;

    @ManyToOne
    @JoinColumn(name="paciente_cedula")
    private PacienteEntity paciente_cedula;

}

package com.tadhack.healthclick.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
public class PacienteEntity {
    @Id
    @Column(nullable = false, length = 20)
    private String cedula;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private int edad;

    @Column(nullable = false, length = 50)
    private String ocupacion;

    @Column(nullable = false, length = 50)
    private String fecha_nacimiento;

    @Column(nullable = false, length = 50)
    private String sexo;

    @OneToMany(mappedBy="paciente_cedula")
    private List<AlergiaEntity> alegias;

    @OneToMany(mappedBy="paciente_cedula")
    private List<EnfermedadEntity> enfermedades;

}

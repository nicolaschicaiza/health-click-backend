package com.tadhack.healthclick.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<AlergiaEntity> alergias;

    @OneToMany(mappedBy="paciente_cedula")
    @JsonManagedReference
    private List<EnfermedadEntity> enfermedades;

}

package com.tadhack.healthclick.controller;

import com.tadhack.healthclick.model.PacienteEntity;
import com.tadhack.healthclick.service.PacienteService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "http://localhost:4200")
public class PacienteController {
    //informacion para poder enviar a la consola
    //private static final Logger logger = LoggerFactory.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/paciente")
    public ResponseEntity<List<PacienteEntity>> obtenerPaciente(){
        System.out.println("ENTRA LA PETICION");
        List<PacienteEntity> pacientes = this.pacienteService.listarPacientes();
        System.out.println("productos obtenidos");
        System.out.println(pacientes.get(0).getAlergias().get(0).getNombre());
        System.out.println("productos obtenidos");
        //pacientes.forEach((paciente -> System.out.println(paciente.toString())));
        return ResponseEntity.ok(pacientes);
    }
}

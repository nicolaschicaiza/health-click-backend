package com.tadhack.healthclick.service;

import com.tadhack.healthclick.model.PacienteEntity;
import com.tadhack.healthclick.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImp implements PacienteService{
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<PacienteEntity> listarPacientes() {
        return this.pacienteRepository.findAll();
    }
}

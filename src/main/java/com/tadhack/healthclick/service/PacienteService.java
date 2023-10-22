package com.tadhack.healthclick.service;

import com.tadhack.healthclick.model.PacienteEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PacienteService{
    public List<PacienteEntity> listarPacientes();
}

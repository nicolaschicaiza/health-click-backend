package com.tadhack.healthclick.service;

import com.tadhack.healthclick.model.EnfermedadEntity;
import com.tadhack.healthclick.repository.EnfermedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnfermedadServiceImp implements EnfermedadService {
    @Autowired
    private EnfermedadRepository enfermedadRepository;


    @Override
    public List<EnfermedadEntity> ListarEnfermedades() {
        return this.enfermedadRepository.findAll();
    }
}

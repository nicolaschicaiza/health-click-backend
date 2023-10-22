package com.tadhack.healthclick.repository;

import com.tadhack.healthclick.model.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity,String> {

}

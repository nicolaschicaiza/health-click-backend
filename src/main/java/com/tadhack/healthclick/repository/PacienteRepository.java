package com.tadhack.healthclick.repository;

import com.tadhack.healthclick.model.EnfermedadEntity;
import com.tadhack.healthclick.model.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Set;


public interface PacienteRepository extends ListCrudRepository<PacienteEntity, String> {


}

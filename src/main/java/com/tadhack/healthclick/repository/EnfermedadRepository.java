package com.tadhack.healthclick.repository;

import com.tadhack.healthclick.model.EnfermedadEntity;
import com.tadhack.healthclick.model.PacienteEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface EnfermedadRepository extends ListCrudRepository<EnfermedadEntity, Integer> {
}

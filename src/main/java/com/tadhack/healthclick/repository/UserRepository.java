package com.tadhack.healthclick.repository;


import com.tadhack.healthclick.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}

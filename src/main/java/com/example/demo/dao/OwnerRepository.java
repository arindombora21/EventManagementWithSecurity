package com.example.demo.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner,Long>{
    public Owner findByUsername(String username);
}

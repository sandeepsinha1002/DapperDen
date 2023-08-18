package com.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}

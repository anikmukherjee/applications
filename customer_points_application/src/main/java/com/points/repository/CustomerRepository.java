package com.points.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.points.entity.Customer;

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    public Customer findByCustomerId(Long customerId);
    
}

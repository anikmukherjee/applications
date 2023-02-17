package com.points.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.points.entity.CustomerTransaction;

@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<CustomerTransaction,Long> {
    public List<CustomerTransaction> findAllByCustomerId(Long customerId);

    public List<CustomerTransaction> findAllByCustomerIdAndDateBetween(Long customerId, Timestamp from,Timestamp to);
}

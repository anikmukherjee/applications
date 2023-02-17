package com.points.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class CustomerTransaction 
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name="CUSTOMER_ID")
    private Long customerId;

    @Column(name = "TRANSACTION_DATE")
    private Timestamp date;

    @Column(name = "AMOUNT")
    private double amount;
    
    public CustomerTransaction()
    {
    }
    
    public CustomerTransaction(Long transactionId, Long customerId, Timestamp date, double amount)
    {
    	this.transactionId = transactionId;
    	this.customerId = customerId;
    	this.date = date;
    	this.amount = amount;
    }

    public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

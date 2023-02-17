package com.points.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.points.entity.Customer;
import com.points.repository.CustomerRepository;
import com.points.service.PointsService;
import com.points.vo.PointsVO;

@RestController
@RequestMapping("/customers")
public class PointsController {

    @Autowired
    PointsService pointsService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PointsVO> getPointsByCustomerId(@PathVariable("customerId") Long customerId){
        Customer customer = customerRepository.findByCustomerId(customerId);
        if(customer == null)
        {
        	throw new RuntimeException("Invalid / Missing customer Id ");
        }
        PointsVO customerRewards = pointsService.getPointsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards,HttpStatus.OK);
    }

}

package com.exp.Ass05Thymeleaf.controller;


import com.exp.Ass05Thymeleaf.data_access.CustomerRepo;
import com.exp.Ass05Thymeleaf.models.Customer;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CustomerController {
    // Configure some endpoints to manage crud
    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo= customerRepo;
    }


    //give me all
    @RequestMapping(value="/api/customers", method = RequestMethod.GET)
    public ArrayList<Customer> getAllCustomers(){
        return customerRepo.getAllCustomers();
    }

    //give me one by ID
    @RequestMapping(value = "api/customer", method = RequestMethod.GET)
    public Customer getCustomerByQueryId(@RequestParam(value="id", defaultValue = "ALFKI") String id){
        return customerRepo.getCustomerById(id);
    }

    // add new one
    @RequestMapping(value = "api/customers", method = RequestMethod.POST)
    public Boolean addNewCustomer(@RequestBody Customer customer){
        return customerRepo.addCustomer(customer);
    }

    //update one
    @RequestMapping(value = "api/customers/{id}", method = RequestMethod.PUT)
    public Boolean updateExistingCustomer(@PathVariable String id, @RequestBody Customer customer){
        return customerRepo.updateCustomer(customer);
    }


}


package com.spring.professional.exam.tutorial.module06.question02.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.professional.exam.tutorial.module06.question02.ds.Customer;
import com.spring.professional.exam.tutorial.module06.question02.service.CustomerService;

@Controller
//@Secured("ROLE_CUSTOMER_READ")
public class CustomersController {

    @Autowired
    private CustomerService customerService;

//    @Secured({"ROLE_CUSTOMER_READ", "ROLE_EMPLOYEES_READ"})
    @GetMapping("/customers")
    public ModelAndView index() {
    	customerService.findByOne(1);
    	
        return new ModelAndView("customers", "customers", customerService.findAll());
    }

//    @Secured("ROLE_CUSTOMER_CREATE")
    @GetMapping("/customers/create")
    public ModelAndView create() {
        return new ModelAndView("customer-create", "customer", new Customer());
    }

//    @Secured("ROLE_CUSTOMER_CREATE")
    @PostMapping("/customers/create")
    public String create(@ModelAttribute Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customer-create";
        } else {
        	List<Customer> aa = new ArrayList<>();
        	aa.add(customer);
        	customerService.save(aa);

            return "redirect:/customers";
        }
    }
//    @Secured("ROLE_CUSTOMER_DELETE")
    @GetMapping("/customers/delete/{id}")
    public String delete(@PathVariable Integer id) {
    	customerService.deleteById(id);

        return "redirect:/customers";
    }
}

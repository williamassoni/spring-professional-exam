package com.spring.professional.exam.tutorial.module06.question02.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.professional.exam.tutorial.module06.question02.dao.EmployeesDao;
import com.spring.professional.exam.tutorial.module06.question02.ds.Employee;

@Controller
public class EmployeesController {

    @Autowired
    private EmployeesDao employeesDao;

/*    @Secured("ROLE_EMPLOYEES_READ")
    @RolesAllowed("ROLE_EMPLOYEES_READ")
    @PreAuthorize("hasRole('ROLE_EMPLOYEES_READ') || hasRole('ROLE_SUPER_ADMIN')")*/
    @GetMapping("/employees")
    public ModelAndView index() {
        return new ModelAndView("employees", "employees", employeesDao.findAll());
    }

    @RolesAllowed("ROLE_EMPLOYEES_CREATE")
    @Secured("ROLE_EMPLOYEES_CREATE")
    @PreAuthorize("hasRole('ROLE_EMPLOYEES_READ') || hasRole('ROLE_EMPLOYEES_CREATE') || hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("/employees/create")
    public ModelAndView create() {
        return new ModelAndView("employee-create", "employee", new Employee());
    }

    @RolesAllowed("ROLE_EMPLOYEES_CREATE")
    @Secured("ROLE_EMPLOYEES_CREATE")
    @PreAuthorize("(hasRole('ROLE_EMPLOYEES_CREATE') || hasRole('ROLE_SUPER_ADMIN')) || #employee.getFirstName() == 'POTATO' ")
    @PostMapping("/employees/create")
    public String create(@ModelAttribute Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee-create";
        } else {
            employeesDao.save(employee);

            return "redirect:/employees";
        }
    }

    @RolesAllowed("ROLE_EMPLOYEES_DELETE")
    @Secured("ROLE_EMPLOYEES_DELETE")
    @PreAuthorize("hasRole('ROLE_EMPLOYEES_DELETE') || hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("/employees/delete/{id}")
    public String delete(@PathVariable Integer id) {
        employeesDao.deleteById(id);

        return "redirect:/employees";
    }
}

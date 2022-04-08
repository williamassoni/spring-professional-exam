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

import com.spring.professional.exam.tutorial.module06.question02.dao.DepartmentsDao;
import com.spring.professional.exam.tutorial.module06.question02.ds.Department;

@Secured("ROLE_DEPARTMENTS_CREATE")
@Controller
public class DepartmentsController {

    @Autowired
    private DepartmentsDao departmentsDao;

    @Secured("ROLE_DEPARTMENTS_READ")
    @RolesAllowed({"ROLE_DEPARTAMENTS_READ","ROLE_SUPER_ADMIN"})
    @PreAuthorize("hasRole('ROLE_DEPARTAMENTS_READ') || hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("/departments")
    public ModelAndView index() {
        return new ModelAndView("departments", "departments", departmentsDao.findAll());
    }

    @Secured("ROLE_DEPARTMENTS_CREATE")
    @RolesAllowed(value = {"ROLE_DEPARTMENTS_CREATE","ROLE_SUPER_ADMIN"})
    @PreAuthorize("hasRole('ROLE_DEPARTMENTS_CREATE') || hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("/departments/create")
    public ModelAndView create() {
        return new ModelAndView("department-create", "department", new Department());
    }

    @Secured("ROLE_DEPARTMENTS_CREATE")
    @RolesAllowed(value = {"ROLE_DEPARTMENTS_CREATE", "ROLE_SUPER_ADMIN"})
    @PreAuthorize("hasRole('ROLE_DEPARTMENTS_CREATE') || hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/departments/create")
    public String create(@ModelAttribute Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "department-create";
        } else {
            departmentsDao.save(department);

            return "redirect:/departments";
        }
    }

    @RolesAllowed(value = {"ROLE_DEPARTMENTS_DELETE", "ROLE_SUPER_ADMIN"})
    @Secured("ROLE_DEPARTMENTS_DELETE")
    @PreAuthorize("hasRole('ROLE_DEPARTMENTS_DELETE') || hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("/departments/delete/{id}")
    public String delete(@PathVariable Integer id) {
        departmentsDao.deleteById(id);

        return "redirect:/departments";
    }
}

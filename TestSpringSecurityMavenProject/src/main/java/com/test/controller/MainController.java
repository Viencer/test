package com.test.controller;

import com.test.model.Personal;
import com.test.service.UserServiceOtherTables;
import com.test.service.UserServicePatient;
import com.test.service.UserServicePersonal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {

    private static Logger logger = Logger.getLogger(MainController.class);

    private UserServicePatient userServicePatient;

    @Autowired
    public void setUserServicePatient(UserServicePatient userServicePatient) {
        this.userServicePatient = userServicePatient;
    }

    private UserServicePersonal userServicePersonal;

    @Autowired
    public void setUserServicePersonal(UserServicePersonal userServicePersonal) {
        this.userServicePersonal = userServicePersonal;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        logger.debug("main index page");
        return "index";
    }

    private UserServiceOtherTables userServiceOtherTables;

    @Autowired
    public void setUserServiceOtherTables(UserServiceOtherTables userServiceOtherTables) {
        this.userServiceOtherTables = userServiceOtherTables;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user(Principal principal, ModelAndView model) {
        Personal personal = userServicePersonal.getByUserNamePersonal(principal.getName());
        model.addObject("person", personal);
        model.addObject("listPatient", userServicePatient.getByIdPatientList(personal.getPatient_id()));
        model.setViewName("user");
        logger.debug("call user page");
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(ModelAndView model) {
        model.addObject("listPersonal", userServicePersonal.allPersonal());
        model.addObject("listPatient", userServicePatient.allPatient());
        model.addObject("department", userServiceOtherTables.getAllDepartments());
        model.addObject("jobs", userServiceOtherTables.getAllJobs());
        model.setViewName("admin");
        logger.debug("call admin page");
        return model;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_INTERN')")
    @RequestMapping(value = "/intern", method = RequestMethod.GET)
    public ModelAndView intern(Principal principal, ModelAndView model) {
        Personal personal = userServicePersonal.getByUserNamePersonal(principal.getName());
        model.addObject("person", personal);
        model.addObject("listPatient", userServicePatient.getByIdPatientList(personal.getPatient_id()));
        model.setViewName("intern");
        logger.debug("call intern page");
        return model;
    }
}
package com.test.controller;

import com.test.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CreateController {
    private static Logger logger = Logger.getLogger(CreateController.class);
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/create")
    public ModelAndView add(ModelAndView model) {
        model.setViewName("create");
        logger.debug("call create controller");
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/createP", method = RequestMethod.POST)
    public ModelAndView create(ModelAndView model, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                               @RequestParam("bossId") int bossId, @RequestParam("com") int com,
                               @RequestParam("salary") int salary, @RequestParam("jobId") int jobId,
                               @RequestParam("username") String username, @RequestParam("password") String password,
                               @RequestParam("department") int department, @RequestParam("patient") Integer patient) {
        userService.createPersonal(firstName, lastName, bossId, com, salary, jobId, department, patient, username, password);
        model.addObject("msg",  "personal added");
        model.setViewName("create");
        logger.debug("call create controller with param");
        return model;
    }

}

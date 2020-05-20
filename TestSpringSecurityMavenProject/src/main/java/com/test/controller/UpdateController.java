package com.test.controller;

import com.test.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateController {

    private static Logger logger = Logger.getLogger(UpdateController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/update/{personalId}")
    public ModelAndView findById(ModelAndView model, @PathVariable("personalId") int personalId) {
        model.addObject("Personal", userService.getByIdPersonal(personalId));
        model.setViewName("update");
        logger.debug("called update controller with id to update: " + personalId);
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/updateP", method = RequestMethod.POST)
    public ModelAndView update(ModelAndView model, @RequestParam("id") int id, @RequestParam("lastName") String lastName,
                               @RequestParam("bossId") int bossId, @RequestParam("com") int com,
                               @RequestParam("salary") int salary, @RequestParam("jobId") int jobId,
                               @RequestParam("department") int department, @RequestParam("patient") Integer patient) {
        userService.updatePersonal(id, lastName, bossId, com, salary, jobId, department, patient);
        model.addObject("msg", "personal updated");
        System.out.println("ALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
        model.setViewName("update");
        logger.debug("called update controller with param");
        return model;
    }

}

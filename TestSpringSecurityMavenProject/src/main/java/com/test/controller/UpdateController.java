package com.test.controller;

import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class UpdateController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/update/{personalId}")
    public ModelAndView findById(ModelAndView model, @PathVariable("personalId") int personalId) throws SQLException {
        model.addObject("Personal", userService.getById(personalId));
        model.setViewName("update");
        return model;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(ModelAndView model, @RequestParam("id") int id, @RequestParam("lastName") String lastName,
                               @RequestParam("bossId") int bossId, @RequestParam("exp") int exp,
                               @RequestParam("salary") int salary, @RequestParam("jobId") int jobId) throws SQLException {
        userService.update(id, lastName, bossId, exp, salary, jobId);
        model.addObject("msg", "personal updated");
        model.setViewName("update");
        return model;
    }

}

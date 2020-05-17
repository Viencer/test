package com.test.controller;

import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class CreateController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create")
    public ModelAndView add(ModelAndView model) {
        model.setViewName("create");
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/createP", method = RequestMethod.POST)
    public ModelAndView create(ModelAndView model, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                               @RequestParam("bossId") int bossId, @RequestParam("exp") int exp,
                               @RequestParam("salary") int salary, @RequestParam("jobId") int jobId,
                               @RequestParam("username") String username, @RequestParam("password") String password) throws SQLException {
        userService.create(firstName, lastName, bossId, exp, salary, jobId, username, password);
        model.addObject("msg", "personal added");
        model.setViewName("create");
        return model;
    }

}

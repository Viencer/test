package com.test.controller;

import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class DeleteController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete/{personalId}")
    public ModelAndView delete(ModelAndView model, @PathVariable("personalId") int personalId) throws SQLException {
        userService.delete(personalId);
        model.setViewName("delete");
        return model;
    }

}

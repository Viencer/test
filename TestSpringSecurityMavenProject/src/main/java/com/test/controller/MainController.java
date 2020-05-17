package com.test.controller;

import com.test.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.NamingException;
import java.security.Principal;
import java.sql.SQLException;

@Controller
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user(Principal principal, ModelAndView model) throws SQLException {
        model.addObject("person", userService.getByName(principal.getName()));
        model.setViewName("user");
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(ModelAndView model) throws SQLException, NamingException {
        model.addObject("listPersonal", userService.allPersonal());
        model.setViewName("admin");
        return model;
    }


}
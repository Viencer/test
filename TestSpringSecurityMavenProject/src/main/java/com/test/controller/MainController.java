package com.test.controller;

import com.test.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MainController {

    private static Logger logger = Logger.getLogger(MainController.class);

    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        logger.debug("main index page");
        return "index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user(Principal principal, ModelAndView model){
        model.addObject("person", userService.getByUserNamePersonal(principal.getName()));
        model.setViewName("user");
        logger.debug("call user page page");
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(ModelAndView model) {
        model.addObject("listPersonal", userService.allPersonal());
        model.setViewName("admin");
        logger.debug("call admin page page");
        return model;
    }


}
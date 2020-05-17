package com.test.controller;

import com.test.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteController {

    private static Logger logger = Logger.getLogger(DeleteController.class);


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete/{personalId}")
    public ModelAndView delete(ModelAndView model, @PathVariable("personalId") int personalId) {
        userService.deletePersonal(personalId);
        model.setViewName("delete");
        logger.debug("call delete controller with delete id: " + personalId);
        return model;
    }

}

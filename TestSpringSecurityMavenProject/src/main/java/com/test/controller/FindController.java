package com.test.controller;

import com.test.dao.DaoConnectionImpl;
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
public class FindController {

    private static Logger logger = Logger.getLogger(FindController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/findP")
    public ModelAndView add(ModelAndView model) {
        model.setViewName("findBy");
        logger.debug("call find controller");
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    public ModelAndView create(ModelAndView model, @RequestParam("lastName") String lastName) {
        model.addObject("listPersonal",  userService.findByLastName(lastName));
        model.setViewName("findBy");
        logger.debug("call find controller with name "+ lastName);
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public ModelAndView create(ModelAndView model, @RequestParam("id") int id) {
        model.addObject("listPersonal",  userService.getByIdPersonalList(id));
        model.setViewName("findBy");
        logger.debug("call find controller with id " + id);
        return model;
    }
}

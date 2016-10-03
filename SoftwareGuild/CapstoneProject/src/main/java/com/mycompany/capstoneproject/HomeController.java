package com.mycompany.capstoneproject;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/"})
public class HomeController {

    public HomeController() {
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {

        return "index";
    }

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String blogHome() {

        return "blog";
    }

    @RequestMapping(value = "/tinymce_prototype", method = RequestMethod.GET)
    public String tinymceHome() {

        return "tinymce_prototype";
    }

}

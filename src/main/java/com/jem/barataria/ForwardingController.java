package com.jem.barataria;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ForwardingController {

    @RequestMapping(value = "/**/{path:[^.]*}")
    public String redirect() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/{path:[^\\.]*}", method = RequestMethod.GET)
    public String redirectToIndex() {
        return "forward:/index.html";
    }
}

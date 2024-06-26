package com.jem.barataria;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ForwardingController {
    @RequestMapping(value = "/**/{path:[^.]*}", method = RequestMethod.GET)
    public String redirect() {
        return "forward:/index.html";
    }
}

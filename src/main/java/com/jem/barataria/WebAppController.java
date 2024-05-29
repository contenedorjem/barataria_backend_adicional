package com.jem.barataria;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebAppController {

    // Captura todas las rutas excepto las que contienen un punto (para recursos estáticos) y las rutas API específicas
    @RequestMapping(value = "/{path:[^\\.]*}", method = RequestMethod.GET)
    public String redirect() {
        // Redirige al archivo index.html servido desde static, permitiendo que Angular maneje el enrutamiento
        return "forward:/index.html";
    }
}

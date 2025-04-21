package top.daisy_dev.audit_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProvisionalController {

    @GetMapping("/home")
    public String showIndex() {
        return "/index";
    }

}

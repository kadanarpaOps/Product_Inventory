package top.daisy_dev.audit_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProvisionalController {

    @GetMapping
    public String showIndex() {
        return "index";
    }

}

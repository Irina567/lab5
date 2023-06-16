package com.irinaExamlpe.petApplication

import com.irinaExamlpe.petApplication.dto.LoginDto
import com.irinaExamlpe.petApplication.dto.RegisterDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
@Controller
class WebController {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("registerDTO", RegisterDto())
        model.addAttribute("loginDTO", LoginDto())
        return "index"
    }
}
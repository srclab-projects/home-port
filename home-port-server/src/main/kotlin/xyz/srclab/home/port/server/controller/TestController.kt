package xyz.srclab.home.port.server.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
open class TestController {

    @RequestMapping("hello")
    open fun hello(): String {
        return "hello"
    }

    @RequestMapping("welcome")
    open fun welcome(): String {
        return "welcome"
    }
}
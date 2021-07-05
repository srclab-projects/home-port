package xyz.srclab.home.port.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class Starter

fun main(args: Array<String>) {
    runApplication<Starter>(*args)
}
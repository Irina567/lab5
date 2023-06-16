package com.irinaExamlpe.petApplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class FoodApplication

fun main(args: Array<String>) {
	runApplication<FoodApplication>(*args)
}

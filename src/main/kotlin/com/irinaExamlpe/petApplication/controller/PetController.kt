package com.irinaExamlpe.petApplication.controller

import com.irinaExamlpe.petApplication.model.Pet
import com.irinaExamlpe.petApplication.service.PetService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/pets")
class PetController(private val service: PetService) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getPets(): Collection<Pet> = service.getPets()


    @GetMapping("/{id}")
    fun getPet(@PathVariable id: Int): Pet = service.getPet(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addPet(@RequestBody pet: Pet): Pet = service.addPet(pet)

    @PatchMapping
    fun updatePet(@RequestBody pet: Pet): Pet = service.updatePet(pet)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePet(@PathVariable id: Int): Unit = service.deletePet(id)
}
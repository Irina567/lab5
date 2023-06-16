package com.irinaExamlpe.petApplication.service

import com.irinaExamlpe.petApplication.datasource.PetDataSource
import com.irinaExamlpe.petApplication.model.Pet
import org.springframework.stereotype.Service

@Service
class PetService(private val dataSource: PetDataSource) {

    fun getPets(): Collection<Pet> = dataSource.retrievePets()

    fun getPet(id: Int) = dataSource.retrievePet(id)

    fun addPet(pet: Pet): Pet = dataSource.createPet(pet)

    fun updatePet(pet: Pet): Pet = dataSource.updatePet(pet)

    fun deletePet(id: Int): Unit = dataSource.deletePet(id)
}
package com.irinaExamlpe.petApplication.datasource

import com.irinaExamlpe.petApplication.model.Pet

interface PetDataSource {
    fun retrievePets(): Collection<Pet>

    fun retrievePet(id: Int): Pet

    fun createPet(pet: Pet): Pet

    fun updatePet(pet: Pet): Pet

    fun deletePet(id: Int): Unit
}
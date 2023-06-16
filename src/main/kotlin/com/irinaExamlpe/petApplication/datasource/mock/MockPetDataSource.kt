package com.irinaExamlpe.petApplication.datasource.mock

import com.irinaExamlpe.petApplication.datasource.PetDataSource
import com.irinaExamlpe.petApplication.model.Pet
import org.springframework.stereotype.Repository


@Repository
class MockPetDataSource: PetDataSource {

    val pets = mutableListOf(
        Pet(1, "Мей-кун", "Для особо привлекательной внешности мейн кунов придется провести тщательный уход.","Баранина"),
        Pet(2, "Манчкин", "Желательно каждый день вычесывать шерсть, а затем погладить ее смоченной в воде рукой.","Не жирное мясо"),
        Pet(3, "Бурма", "Представители породы не требуют серьезного ухода, короткую шерсть достаточно причесывать раз в две недели, а мыть бурму нужно не чаще 2-3 раз в год. ","Отварное нежирное мясо")
    )

    override fun retrievePets(): Collection<Pet> = pets

    override fun retrievePet(id: Int): Pet = pets.firstOrNull() { it.id == id }
        ?: throw NoSuchElementException("Could not find a pet with account number $id")

    override fun createPet(pet: Pet): Pet {
        if (pets.any {it.id == pet.id}) {
            throw IllegalArgumentException("Pet with account number ${pet.id} already exist")
        }
        pets.add(pet)
        return pet
    }

    override fun updatePet(pet: Pet): Pet {
        val currentPet = pets.firstOrNull() { it.id == pet.id }
            ?: throw NoSuchElementException("Could not find a pet with account number ${pet.id}")

        pets.remove(currentPet)
        pets.add(pet)

        return pet
    }

   override fun deletePet(id: Int) {
        val currentPet = pets.firstOrNull() { it.id == id }
            ?: throw NoSuchElementException("Could not find a pet with account number $id")

        pets.remove(currentPet)
    }
}
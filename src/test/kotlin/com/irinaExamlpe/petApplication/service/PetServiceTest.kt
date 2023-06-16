package com.tdmnsExamlpe.FoodApplication.service

import com.irinaExamlpe.petApplication.datasource.PetDataSource
import com.irinaExamlpe.petApplication.service.PetService
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class PetServiceTest {

    private val dataSource: PetDataSource = mockk(relaxed = true)
    private val petService = PetService(dataSource)

    @Test
    fun `should call its data source to retrieve foods`() {
        // when
        petService.getFoods()

        // then
        verify(exactly = 1) { dataSource.retrieveFoods() }
    } 
}
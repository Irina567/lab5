package com.irinaExamlpe.petApplication.datasource.mock

import org.assertj.core.api.Assertions.assertThat


class MockPetDataSourceTest {

    private val mockDataSource = MockPetDataSource()

    @org.junit.jupiter.api.Test
    fun `should provide a collection of foods`() {
        // when
        val foods = mockDataSource.retrieveFoods()

        // then
        assertThat(foods.size).isGreaterThanOrEqualTo(3)
    }

    @org.junit.jupiter.api.Test
    fun `should provide some mock data`() {
        // when
        val foods = mockDataSource.retrieveFoods()

        // then
        assertThat(foods).allMatch { it.Product.isNotBlank() }
        assertThat(foods).anyMatch { it.Inf_ob_product.isNotBlank() }
        assertThat(foods).anyMatch { it.Rechepti.isNotBlank() }
    } 
}
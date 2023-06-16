package com.irinaExamlpe.petApplication.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.irinaExamlpe.petApplication.model.Pet
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {
    private val baseUrl = "/api/foods"

    @Nested
    @DisplayName("GET /api/foods")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all foods`() {
            // when / then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") {
                        value(1)
                    }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/foods/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should return single food with the given account number`() {
            // given
            val id = 1

            // when / then
            mockMvc.get("/api/foods/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.id") {
                        value(id)
                    }
                }
        }

        @Test
        fun `should return NOT FOUND if the account number doesnt exist`() {
            // given
            val id = "does_not_exist"

            // when / then
            mockMvc.get("/api/foods/$id")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("POST /api/foods")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AddPet{

        @Test
        fun `should add the new food`() {
            // given
            val id = 1
            val Product = "Папоротник"
            val Inf_ob_product = "Очишуенный"
            val Rechepti = "Суп"
            val newPet= Pet(id = id, Product= Product, Inf_ob_product= Inf_ob_product, Rechepti=Rechepti)
            // when
            val performPost = mockMvc.post("/api/foods") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newPet)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newPet))
                    }
                }

            mockMvc.get("/api/foods/${newPet.id}")
                .andExpect {
                    content { json(objectMapper.writeValueAsString(newPet)) }
                }
        } 
        
        @Test
        fun `should return BAD REQUEST if food with given number already exist`() {
            // given
            val invalidPet = Pet(1, "Помидорчик", "Прикол","Суп")
            
            // when
            val performPost = mockMvc.post("/api/foods") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidPet)
            }
            
            // then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }
            
        } 
    }
    
    @Nested
    @DisplayName("PATCH /api/foods")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExistingPet {
     
        @Test
        fun `should update an existing food`() {
            // given
            val id = 1
            val updatePet = Pet(1, "Помидорчик", "Прикол","Суп")
            
            // when
            val performPatch = mockMvc.patch("/api/foods") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatePet)
            }
            
            // then
            performPatch
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatePet))
                    }
                }

            mockMvc.get("/api/foods/$id")
                .andExpect {
                    content {
                        json(objectMapper.writeValueAsString(updatePet))
                    }
                }
        } 
        
        @Test
        fun `should return BAD REQUEST if no bank with given account number exist`() {
            // given
            val id = 999
            val invalidPet = Pet(1, "Помидорчик", "Прикол","Суп")

            // when
            val performPost = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidPet)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        } 
    }

    @Nested
    @DisplayName("DELETE /api/foods/{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteExistingPet {

        @Test
        @DirtiesContext // if you want change order for tests (f.e.) move delete above GET request
        fun `should delete an existing food`() {
            // given
            val id = 1

            // when / then
            mockMvc.delete("$baseUrl/$id")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$id")
                .andExpect {
                    status { isNotFound() }
                }
        }

        @Test
        fun `should return NOT FOUND if no bank with given account number exist`() {
            // given
            val id = 999

            // when / then
            mockMvc.delete("$baseUrl/$id")
                .andExpect { status { isNotFound() } }
        }
    }
}
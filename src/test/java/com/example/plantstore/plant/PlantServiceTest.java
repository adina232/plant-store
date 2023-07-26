package com.example.plantstore.plant;

import com.example.plantstore.cart.PlantList;
import com.example.plantstore.cart.PlantListRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PlantServiceTest {


    @Test
    void deletePlant() {
        PlantRepository plantRepository = mock(PlantRepository.class);
        PlantListRepository plantListRepository = mock(PlantListRepository.class);

        PlantService plantService = new PlantService(plantRepository, plantListRepository);


        plantService.deletePlant(1);


        verify(plantRepository).deleteById(eq(1));
    }

    @Test
    public void getCatalogue_whenThereIsNoPlant_expectEmptyList() {
        PlantRepository plantRepository = mock(PlantRepository.class);
        PlantListRepository plantListRepository = mock(PlantListRepository.class);

        PlantService plantService = new PlantService(plantRepository, plantListRepository);


        when(plantRepository.findAll()).thenReturn(new ArrayList<>());

        List<Plant> result = plantService.getCatalogue();

        assertTrue(result.isEmpty());
    }


    @Test
    public void getCatalogue_whenThereArePlants_expectPlants() {
        PlantRepository plantRepository = mock(PlantRepository.class);
        PlantListRepository plantListRepository = mock(PlantListRepository.class);

        PlantService plantService = new PlantService(plantRepository, plantListRepository);

        Plant plant = new Plant();
        plant.setName("Test");
        plant.setId(1);
        plant.setPrice(20);

        List<Plant> catalog = new ArrayList<>();
        catalog.add(plant);

        when(plantRepository.findAll()).thenReturn(catalog);

        List<Plant> result = plantService.getCatalogue();

        assertFalse(result.isEmpty());
        assertEquals("Test", result.get(0).getName());
        assertEquals(1, result.get(0).getId());
        assertEquals(20, result.get(0).getPrice(), "Expected price 20 ");
    }

    @Test
    void addPlant(){
        PlantRepository plantRepository = mock(PlantRepository.class);
        PlantListRepository plantListRepository = mock(PlantListRepository.class);

        PlantService plantService = new PlantService(plantRepository, plantListRepository);

        Plant plant = new Plant();
        plant.setName("Test");
        plant.setId(1);
        plant.setPrice(200);

        Plant plant2 = new Plant();
        plant2.setName("Tests");
        plant2.setId(1);
        plant2.setPrice(200);

        plantService.addPlant(plant);

        verify(plantRepository).save(eq(plant));
    }

    @Test
    void getMyCart_EmptyCart() {
        PlantRepository plantRepository = mock(PlantRepository.class);
        PlantListRepository plantListRepository = mock(PlantListRepository.class);

        PlantService plantService = new PlantService(plantRepository, plantListRepository);

        when(plantListRepository.findAll()).thenReturn(new ArrayList<>());

        List<PlantList> result = plantService.getMyCart();

        assertTrue(result.isEmpty());
    }

    @Test
    void getMyCart_ThereShouldBeAPlantList() {
        PlantRepository plantRepository = mock(PlantRepository.class);
        PlantListRepository plantListRepository = mock(PlantListRepository.class);

        PlantService plantService = new PlantService(plantRepository, plantListRepository);

        PlantList plant = new PlantList();
        plant.setName("Test");
        plant.setId(1);
        plant.setPrice(20.0);

        List<PlantList> myCart = new ArrayList<>();
        myCart.add(plant);

        when(plantListRepository.findAll()).thenReturn(myCart);

        List<PlantList> result = plantService.getMyCart();

        assertFalse(result.isEmpty());
        assertEquals("Test", result.get(0).getName());
        assertEquals(1, result.get(0).getId());
        assertEquals(20, result.get(0).getPrice(), "Expected price 20 ");
    }
}
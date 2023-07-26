package com.example.plantstore.cart;

import com.example.plantstore.plant.Plant;
import com.example.plantstore.plant.PlantRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class PlantListServiceTest {

    @Test
    void addToCart() {
        PlantRepository plantRepository = mock(PlantRepository.class);
        PlantListRepository plantListRepository = mock(PlantListRepository.class);
        PlantListService plantListService = new PlantListService(plantListRepository, plantRepository);

        Plant plant = new Plant();
        plant.setName("Test");
        plant.setId(1);
        plant.setPrice(20.0);

        when(plantRepository.findById(1)).thenReturn(Optional.of(plant));

        plantListService.addToCart(1);

        PlantList plantList = new PlantList();
        plantList.setName("Test");
        plantList.setPrice(20.0);
        verify(plantListRepository).save(plantList);
    }

    @Test
    void addToCart_whenIdIsNotOk_expectedNotToSaveAPlantList() {
        PlantListRepository plantListRepository = mock(PlantListRepository.class);
        PlantRepository plantRepository = mock(PlantRepository.class);
        PlantListService plantListService = new PlantListService(plantListRepository, plantRepository);

        when(plantRepository.findById(1)).thenReturn(Optional.empty());

        plantListService.addToCart(1);

        verify(plantListRepository, never()).save(any());
    }

    @Test
    void deletePlantList() {
        PlantRepository plantRepository = mock(PlantRepository.class);
        PlantListRepository plantListRepository = mock(PlantListRepository.class);
        PlantListService plantListService = new PlantListService(plantListRepository, plantRepository);

        PlantList plant = new PlantList(1, "Test", 20.0);

        plantListService.deletePlantList(1);

        verify(plantListRepository).deleteById(1);
    }
}
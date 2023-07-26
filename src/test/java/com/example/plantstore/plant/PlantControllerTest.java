package com.example.plantstore.plant;

import com.example.plantstore.cart.PlantList;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlantControllerTest {

    @Test
    public void getCatalogue() {
        PlantService plantService = mock(PlantService.class);
        PlantController plantController = new PlantController(plantService);

        Plant plant = new Plant();
        plant.setId(1);
        plant.setPrice(10);
        plant.setName("Name");
        List<Plant> plants = new ArrayList<>();
        plants.add(plant);

        when(plantService.getCatalogue()).thenReturn(plants);

        ModelAndView result = plantController.getCatalogue();

        assertEquals("catalogue", result.getViewName());
        assertTrue(result.getModel().containsKey("catalogue"));
        assertFalse(((List<Plant>) result.getModel().get("catalogue")).isEmpty());
        assertEquals(plant, ((List<Plant>) result.getModel().get("catalogue")).get(0));
    }

    @Test
    void home() {
        PlantService plantService = mock(PlantService.class);
        PlantController plantController = new PlantController(plantService);

        String result = plantController.home();

        assertEquals("home", result);
    }

    @Test
    void newPlant() {
        PlantService plantService = mock(PlantService.class);
        PlantController plantController = new PlantController(plantService);

        ModelAndView result = plantController.newPlant();

        assertEquals("addPlant", result.getViewName());
        assertTrue(result.getModel().containsKey("plant"));
        assertEquals(new Plant(), result.getModel().get("plant"));
    }

    @Test
    void addPlant() {
        PlantService plantService = mock(PlantService.class);
        PlantController plantController = new PlantController(plantService);
        Plant plant = new Plant();
        plant.setId(1);
        plant.setPrice(20.0);
        plant.setName("test");

        assertEquals("redirect:/catalogue", plantController.addPlant(plant));
        verify(plantService).addPlant(eq(plant));
    }

    @Test
    void deletePlant() {
        PlantService plantService = mock(PlantService.class);
        PlantController plantController = new PlantController(plantService);

        Integer id = 1;

        assertEquals("redirect:/catalogue", plantController.deletePlant(id));
        verify(plantService).deletePlant(1);
    }

    @Test
    void getMyCart_ShouldReturnEmptyList() {
        PlantService plantService = mock(PlantService.class);
        PlantController plantController = new PlantController(plantService);

        List<PlantList> plants = new ArrayList<>();
        when(plantService.getMyCart()).thenReturn(plants);

        ModelAndView result = plantController.getMyCart();

        assertEquals("myCart", result.getViewName());
        assertTrue(result.getModel().containsKey("myCart"));
        assertTrue(((List<PlantList>) result.getModel().get("myCart")).isEmpty());
    }

    @Test
    void getMyCart_ShouldReturnOnePlant() {
        PlantService plantService = mock(PlantService.class);
        PlantController plantController = new PlantController(plantService);

        PlantList plant = new PlantList();
        plant.setId(1);
        plant.setPrice(20.0);
        plant.setName("Test");

        List<PlantList> plants = new ArrayList<>();
        plants.add(plant);

        when(plantService.getMyCart()).thenReturn(plants);

        ModelAndView result = plantController.getMyCart();

        assertEquals("myCart", result.getViewName());
        assertTrue(result.getModel().containsKey("myCart"));
        assertFalse(((List<PlantList>) result.getModel().get("myCart")).isEmpty());
        assertEquals(plant, ((List<PlantList>) result.getModel().get("myCart")).get(0));
    }

}
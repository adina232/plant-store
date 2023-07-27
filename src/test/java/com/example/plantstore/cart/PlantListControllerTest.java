package com.example.plantstore.cart;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PlantListControllerTest {

    @Test
    void addToCart() {
        PlantListService plantListService = mock(PlantListService.class);
        PlantListController plantListController = new PlantListController(plantListService);

        PlantList plantList = new PlantList(1, "Test", 20.0);
        RedirectAttributes redirAttrs = mock(RedirectAttributes.class);


        String result = plantListController.addToCart(1, redirAttrs);

        verify(plantListService).addToCart(1);
        verify(redirAttrs).addFlashAttribute("success", "Produs adaugat in cos");

        assertEquals("redirect:/catalogue", result);
    }


    @Test
    void deletePlantList() {
        PlantListService plantListService = mock(PlantListService.class);
        PlantListController plantListController = new PlantListController(plantListService);

        Integer id = 1;

        String result = plantListController.deletePlantList(id);

        verify(plantListService).deletePlantList(1);
        assertEquals("redirect:/myCart", result);
    }
}
package com.example.plantstore.wishlist;

import com.example.plantstore.plant.Plant;
import com.example.plantstore.plant.PlantRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WishListServiceTest {
    WishListRepository wishListRepository = mock(WishListRepository.class);
    PlantRepository plantRepository = mock(PlantRepository.class);
    WishListService wishListService = new WishListService(wishListRepository, plantRepository);

    @Test
    void getWishList_whenThereIsNoItem_expectEmptyList() {
        List<WishListItem> itemList = new ArrayList<>();

        List<WishListItem> result = wishListService.getWishList();

        verify(wishListRepository).findAll();

        assertEquals(itemList, result);
    }

    @Test
    void getWishList_whenThereAreItems_expectItemsOnList() {

        List<WishListItem> itemList = new ArrayList<>();
        WishListItem wishListItem = new WishListItem(1, "Test", 20.0);
        itemList.add(wishListItem);

        when(wishListRepository.findAll()).thenReturn(itemList);

        List<WishListItem> result = wishListService.getWishList();

        assertEquals(itemList, result);

    }

    @Test
    void addToWishList_whenIdIsPresent_expectToAddToRepository() {
        Plant plant = new Plant();
        plant.setId(1);
        plant.setPrice(20.0);
        plant.setName("Test");

        when(plantRepository.findById(1)).thenReturn(Optional.of(plant));

        wishListService.addToWishList(1);

        WishListItem wishListItem = new WishListItem();
        wishListItem.setPrice(20.0);
        wishListItem.setName("Test");

        verify(wishListRepository).save(wishListItem);
    }

    @Test
    void addToWishList_whenIdIsNotPresent_expectNotToSaveToRepository() {
        Plant plant = new Plant();
        plant.setName("Test");
        plant.setPrice(20.0);
        plant.setId(1);

        when(plantRepository.findById(0)).thenReturn(Optional.empty());

        wishListService.addToWishList(1);

        verify(wishListRepository, never()).save(any());
    }

    @Test
    void deleteWishListItem() {
        Integer id = 1;

        wishListService.deleteWishListItem(1);
        verify(wishListRepository).deleteById(1);
    }
}
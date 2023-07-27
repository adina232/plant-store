package com.example.plantstore.wishlist;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class WishListControllerTest {
    WishListService wishListService = mock(WishListService.class);
    WishListController wishListController = new WishListController(wishListService);

    @Test
    void getWishList_whenListIsEmpty_expectEmptyWishList() {
        List<WishListItem> wishListItems = new ArrayList<>();
        when(wishListService.getWishList()).thenReturn(wishListItems);

        ModelAndView result = wishListController.getWishList();

        assertEquals("wishList", result.getViewName());
        assertTrue(result.getModel().containsKey("wishList"));
        assertTrue(((List<WishListItem>) result.getModel().get("wishList")).isEmpty());
    }

    @Test
    void addToWishList() {
        WishListItem wishListItem = new WishListItem(1, "Test", 20.0);
        RedirectAttributes redirAttr = mock(RedirectAttributes.class);

        String result = wishListController.addToWishList(1, redirAttr);

        verify(wishListService).addToWishList(1);
        verify(redirAttr).addFlashAttribute("success", "Produs adaugat in wish list");
        assertEquals("redirect:/catalogue", result);

    }

    @Test
    void deleteWishListItem() {
        Integer id = 1;
        String result = wishListController.deleteWishListItem(1);
        verify(wishListService).deleteWishListItem(1);

        assertEquals("redirect:/wishList", result);
    }
}
package com.example.plantstore.wishlist;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class WishListController {
    @Autowired
    private WishListService wishListService;
    @GetMapping("/wishList")
    public ModelAndView getWishList() {
        List<WishListItem> wishListItems = wishListService.getWishList();
        return new ModelAndView("wishList", "wishList", wishListItems);
    }

    @GetMapping("/addToWishList")
    public String addToWishList(@RequestParam Integer id, RedirectAttributes redirAttrs) {
        wishListService.addToWishList(id);
        redirAttrs.addFlashAttribute("success", "Produs adaugat in wish list");
        return "redirect:/catalogue";
    }

    @GetMapping("/deleteWishListItem")
    public String deleteWishListItem(@RequestParam Integer id) {
        wishListService.deleteWishListItem(id);
        return "redirect:/wishList";
    }
}

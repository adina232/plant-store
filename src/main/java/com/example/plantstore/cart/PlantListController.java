package com.example.plantstore.cart;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class PlantListController {
    @Autowired
    private PlantListService plantListService;

    @RequestMapping(path = "/myCart/{id}", method = RequestMethod.GET)
    public String addToCart(@PathVariable("id") Integer id, RedirectAttributes redirAttrs) {
        plantListService.addToCart(id);
        redirAttrs.addFlashAttribute("success", "Produs adaugat in cos");
        return "redirect:/catalogue";
    }

    @GetMapping("/deletePlantList")
    public String deletePlantList(@RequestParam Integer id) {
        plantListService.deletePlantList(id);
        return "redirect:/myCart";
    }

}

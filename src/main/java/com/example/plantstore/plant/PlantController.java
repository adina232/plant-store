package com.example.plantstore.plant;

import com.example.plantstore.cart.PlantList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/addPlant")
    public ModelAndView newPlant() {
        ModelAndView mav = new ModelAndView("addPlant");

        mav.addObject("plant", new Plant());

        return mav;
    }

    @PostMapping("/addPlant")
    public String addPlant(@ModelAttribute Plant plant) {
        plantService.addPlant(plant);
        return "redirect:/catalogue";
    }

    @GetMapping("/catalogue")
    public ModelAndView getCatalogue() {
        List<Plant> catalogue = plantService.getCatalogue();
        return new ModelAndView("catalogue", "catalogue", catalogue);
    }

    @GetMapping("/deletePlant")
    public String deletePlant(@RequestParam(value = "id") Integer id) {
        plantService.deletePlant(id);
        return "redirect:/catalogue";
    }

    @GetMapping("/myCart")
    public ModelAndView getMyCart() {
        List<PlantList> myCart = plantService.getMyCart();
        return new ModelAndView("myCart", "myCart", myCart);
    }


}

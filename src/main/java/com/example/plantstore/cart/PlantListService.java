package com.example.plantstore.cart;

import com.example.plantstore.plant.Plant;
import com.example.plantstore.plant.PlantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PlantListService {
    @Autowired
    private PlantListRepository plantListRepository;
    @Autowired
    private PlantRepository plantRepository;

    public void addToCart(Integer id) {
        Optional<Plant> plant = plantRepository.findById(id);
        if (plant.isPresent()) {
            PlantList plantList = new PlantList();
            plantList.setName(plant.get().getName());
            plantList.setPrice(plant.get().getPrice());

            plantListRepository.save(plantList);
        }
    }

    public void deletePlantList(Integer id) {
        plantListRepository.deleteById(id);
    }


}

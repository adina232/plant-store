package com.example.plantstore.plant;

import com.example.plantstore.cart.PlantList;
import com.example.plantstore.cart.PlantListRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private PlantListRepository plantListRepository;

    public void addPlant(Plant plant) {
        plantRepository.save(plant);
    }

    public List<Plant> getCatalogue() {
        return plantRepository.findAll();
    }

    public void deletePlant(Integer id) {
        plantRepository.deleteById(id);
    }

    public List<PlantList> getMyCart() {
        return plantListRepository.findAll();
    }
}

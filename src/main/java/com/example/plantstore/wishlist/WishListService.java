package com.example.plantstore.wishlist;

import com.example.plantstore.plant.Plant;
import com.example.plantstore.plant.PlantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private PlantRepository plantRepository;


    public List<WishListItem> getWishList() {
        return wishListRepository.findAll();
    }

    public void addToWishList(Integer id) {
        Optional<Plant> plant = plantRepository.findById(id);
        if (plant.isPresent()) {
            WishListItem wishListItem = new WishListItem();
            wishListItem.setName(plant.get().getName());
            wishListItem.setPrice(plant.get().getPrice());

            wishListRepository.save(wishListItem);
        }
    }

    public void deleteWishListItem(Integer id) {
        wishListRepository.deleteById(id);
    }
}

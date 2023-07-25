package com.example.plantstore.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantListRepository extends JpaRepository<PlantList, Integer> {
}

package com.testtask.house.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.testtask.house.service.HouseService;
import com.testtask.house.model.House;

import java.util.List;

@RestController
@RequestMapping("/api/houses")
@RequiredArgsConstructor
public class HouseController {


    private final HouseService houseService;


    @GetMapping
    public ResponseEntity<List<House>> getAllHouses() {
        return ResponseEntity.ok(houseService.getAllHouses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getHouseById(@PathVariable Long id) {
        House house = houseService.getHouseById(id);
        if (house != null) {
            return ResponseEntity.ok(house);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{ownerId}")
    public ResponseEntity<House> createHouse(@RequestBody House house, @PathVariable Long ownerId) {
        House createdHouse = houseService.createHouse(house, ownerId);
        if (createdHouse != null) {
            return ResponseEntity.ok(createdHouse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable Long id, @RequestBody House updatedHouse) {
        House house = houseService.getHouseById(id);
        if (house != null) {
            updatedHouse.setId(id);
            return ResponseEntity.ok(houseService.updateHouse(updatedHouse));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouse(@PathVariable Long id) {
        houseService.deleteHouse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{houseId}/addUser")
    public ResponseEntity<Void> addUserToHouse(@PathVariable Long houseId, @RequestBody Long userId, @RequestHeader("Requester-Id") Long requesterId) {
        boolean success = houseService.addUserToHouse(houseId, userId, requesterId);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
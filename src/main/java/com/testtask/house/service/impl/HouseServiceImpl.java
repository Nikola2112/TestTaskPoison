package com.testtask.house.service.impl;

import com.testtask.house.model.House;
import com.testtask.house.repository.HouseRepository;
import com.testtask.house.service.HouseService;
import com.testtask.user.model.User;
import com.testtask.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;


    public House createHouse(House house, Long ownerId) {
        User owner = userRepository.findById(ownerId).orElse(null);
        if (owner != null) {
            house.setOwner(owner);
            return houseRepository.save(house);
        }
        return null;
    }

    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    public House getHouseById(Long id) {
        return houseRepository.findById(id).orElse(null);
    }

    public House updateHouse(House house) {
        return houseRepository.save(house);
    }

    public void deleteHouse(Long id) {
        houseRepository.deleteById(id);
    }

    public boolean addUserToHouse(Long houseId, Long userId, Long requesterId) {
        House house = houseRepository.findById(houseId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (house != null && user != null && house.getOwner().getId().equals(requesterId)) {
            house.getResidents().add(user);
            houseRepository.save(house);
            return true;
        }
        return false;
    }
}
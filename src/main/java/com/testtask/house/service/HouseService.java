package com.testtask.house.service;



import com.testtask.house.model.House;

import java.util.List;
import java.util.Optional;

public interface HouseService {
    House createHouse(House house, Long ownerId);
    House getHouseById(Long id);
    House updateHouse(House house);
    void deleteHouse(Long houseId);
    List<House> getAllHouses();
    boolean addUserToHouse(Long houseId, Long userId, Long requesterId);

}

package com.burgermaster.winners.service;

import com.burgermaster.winners.dto.BurgerDto;

import java.util.List;

public interface IBurgerService {
    void createBurger(BurgerDto burgerDto) throws Exception;
    void updateBurger(String id, BurgerDto request) throws Exception;
    void deleteBurger(String id);
    List<BurgerDto> getBurgers();
    BurgerDto getBurger(String id);
}

package com.burgermaster.winners.service;

import com.burgermaster.winners.dto.BurgerDto;
import com.burgermaster.winners.model.Burger;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class BurgerService implements IBurgerService {
    private final List<BurgerDto> burgers = new ArrayList<>();
    private final Burger burger = new Burger();

    public BurgerService() {
        burgers.add(new BurgerDto("egy-hamburgueseria",
                "Era Gol de Yepes", 300, "Blanco",
                "La Hamburgueseria", 100));
    }

    @Override
    public void createBurger(BurgerDto burgerDto) throws Exception {
        if (burgerExists(burgerDto.getId())) {
            throw new AlreadyBoundException("The burger key is already used");
        } else if (burgerDto.getGrOfMeat() < burger.MIN_GR_OF_MEAT) {
            throw new IllegalArgumentException("The meat gr has to be more than 125gr");
        } else {
            burgers.add(burgerDto);
        }
    }

    @Override
    public void updateBurger(String id, BurgerDto request) throws Exception {
        BurgerDto burgerDto= burgers.stream()
                .filter(currentBurger -> Objects.equals(currentBurger.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("The burger doesnt exists"));
        if(request.getGrOfMeat() < burger.MIN_GR_OF_MEAT) {
            throw new IllegalArgumentException("The meat gr has to be more than 125gr");
        } else {
            burgerDto.setName(request.getName());
            burgerDto.setGrOfMeat(request.getGrOfMeat());
            burgerDto.setTypeOfBread(request.getTypeOfBread());
            burgerDto.setScore(request.getScore());
        }
    }

    @Override
    public void deleteBurger(String id) {
        BurgerDto burgerDto = burgers.stream()
                .filter(currentBurger -> Objects.equals(currentBurger.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("The burger doesnt exists"));
        burgers.remove(burgerDto);
    }

    @Override
    public List<BurgerDto> getBurgers() {
        return burgers;
    }

    @Override
    public BurgerDto getBurger(String id) {
        return burgers.stream()
                .filter(currentBurger -> Objects.equals(currentBurger.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("The burger doesnt exists"));
    }

    private boolean burgerExists(String id) {
        return burgers.stream()
                .filter(currentBurger -> Objects.equals(currentBurger.getId(), id))
                .count() > 0;
    }
}

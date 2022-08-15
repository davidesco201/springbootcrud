package com.burgermaster.winners.service;

import com.burgermaster.winners.dto.BurgerDto;
import com.burgermaster.winners.model.Burger;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BurgerService implements IBurgerService {
    private List<Burger> burgers;

    public BurgerService() {
        this.burgers = setBurgersWinners();
    }

    @Override
    public void createBurger(BurgerDto burgerDto) throws Exception {
        if (burgerExists(burgerDto.getId())) {
            throw new AlreadyBoundException("The burger key is already used");
        } else if (burgerDto.getGrOfMeat() < Burger.MIN_GR_OF_MEAT) {
            throw new IllegalArgumentException("The meat gr has to be more than 125gr"); 

        } else if (burgerDto.getScore() < Burger.MIN_SCORE){
            throw new IllegalArgumentException("The score has to be positive");
        }
        else {
            burgers.add(this.convertDtoToModelEntity(burgerDto));
        }
    }

    @Override
    public void updateBurger(String id, BurgerDto request) throws Exception {
        Burger burger = burgers.stream()
                .filter(currentBurger -> Objects.equals(currentBurger.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("The burger doesnt exists"));
        if (request.getGrOfMeat() < Burger.MIN_GR_OF_MEAT) {
            throw new IllegalArgumentException("The meat gr has to be more than 125gr");
        } else {
            burger.setName(request.getName());
            burger.setGrOfMeat(request.getGrOfMeat());
            burger.setTypeOfBread(request.getTypeOfBread());
            burger.setScore(request.getScore());
        }
    }

    @Override
    public void deleteBurger(String id) {
        Burger burger = burgers.stream()
                .filter(currentBurger -> Objects.equals(currentBurger.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("The burger doesnt exists"));
        burgers.remove(burger);
    }

    @Override
    public List<BurgerDto> getBurgers() {
        return burgers.stream()
                .map(this::convertModelEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BurgerDto getBurger(String id) {
        Burger burger = burgers.stream()
                .filter(currentBurger -> Objects.equals(currentBurger.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("The burger doesnt exists"));
        return convertModelEntityToDto(burger);
    }

    private boolean burgerExists(String id) {
        return burgers.stream()
                .filter(currentBurger -> Objects.equals(currentBurger.getId(), id))
                .count() > 0;
    }

    private BurgerDto convertModelEntityToDto(Burger burger) {
        BurgerDto burgerDto = new BurgerDto();
        burgerDto.setId(burger.getId());
        burgerDto.setName(burger.getName());
        burgerDto.setScore(burger.getScore());
        burgerDto.setTypeOfBread(burger.getTypeOfBread());
        burgerDto.setGrOfMeat(burger.getGrOfMeat());
        burgerDto.setRestaurant(burger.getRestaurant());
        return burgerDto;
    }

    private Burger convertDtoToModelEntity(BurgerDto burgerDto) {
        return new Burger(burgerDto.getId(),
                burgerDto.getName(),
                burgerDto.getGrOfMeat(),
                burgerDto.getTypeOfBread(),
                burgerDto.getRestaurant(),
                burgerDto.getScore());
    }

    private List<Burger> setBurgersWinners() {
        this.burgers = new ArrayList<>();
        this.burgers.add(new Burger("egy-hamburgueseria",
                "Era Gol de Yepes", 300, "Blanco",
                "La Hamburgueseria", 100));
        return this.burgers;
    }
}

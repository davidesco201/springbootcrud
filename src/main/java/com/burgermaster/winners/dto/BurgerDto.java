package com.burgermaster.winners.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BurgerDto {
    private String id;
    private String name;
    private int grOfMeat;
    private String typeOfBread;
    private String restaurant;
    private int score;
}

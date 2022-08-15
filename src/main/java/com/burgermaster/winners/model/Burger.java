package com.burgermaster.winners.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Burger {
    public final static int MIN_GR_OF_MEAT = 125;
    public final static int MIN_SCORE = 0;
    private String id;
    private String name;
    private int grOfMeat;
    private String typeOfBread;
    private String restaurant;
    private int score;

}



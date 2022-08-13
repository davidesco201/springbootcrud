package com.burgermaster.winners.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Burger {
    public final static int MIN_GR_OF_MEAT = 125;

    private String id;
    private String name;
    private int grOfMeat;
    private String typeOfBread;
    private String restaurant;
    private int score;

}



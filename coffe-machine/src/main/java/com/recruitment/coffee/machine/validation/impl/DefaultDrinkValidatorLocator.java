package com.recruitment.coffee.machine.validation.impl;

import com.recruitment.coffee.machine.model.DRINKS;
import com.recruitment.coffee.machine.validation.DrinkValidator;
import com.recruitment.coffee.machine.validation.DrinkValidatorLocator;

public class DefaultDrinkValidatorLocator implements DrinkValidatorLocator {

    private TeaValidator teaValidator;
    private CoffeeValidator coffeeValidator;
    private ChocolateValidator chocolateValidator;

    public DefaultDrinkValidatorLocator(TeaValidator teaValidator, CoffeeValidator coffeeValidator, ChocolateValidator chocolateValidator) {
        this.teaValidator = teaValidator;
        this.coffeeValidator = coffeeValidator;
        this.chocolateValidator = chocolateValidator;
    }

    public DrinkValidator retrieve(DRINKS drinkEnum) {

        switch (drinkEnum) {
            case TEA:
                return teaValidator;
            case COFFEE:
                return coffeeValidator;
            case CHOCOLATE:
                return chocolateValidator;
        }
        return null;
    }
}

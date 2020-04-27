package com.recruitment.coffee.machine.validation.impl;

import com.recruitment.coffee.machine.validation.DrinkValidator;

public class CoffeeValidator implements DrinkValidator {

    private Float price;

    public CoffeeValidator(Float price) {
        this.price = price;
    }

    @Override
    public String validate(Float money) {

        if (money < price) {
            return "The coffee costs " + price + ".";
        }
        return null;
    }
}

package com.recruitment.coffee.machine.validation.impl;

import com.recruitment.coffee.machine.validation.DrinkValidator;

public class ChocolateValidator implements DrinkValidator {

    private Float price;

    public ChocolateValidator(Float price) {
        this.price = price;
    }

    @Override
    public String validate(Float money) {

        if (money < price) {
            return "The chocolate costs " + price + ".";
        }
        return null;
    }
}

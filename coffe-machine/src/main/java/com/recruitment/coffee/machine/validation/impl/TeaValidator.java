package com.recruitment.coffee.machine.validation.impl;

import com.recruitment.coffee.machine.validation.DrinkValidator;

public class TeaValidator implements DrinkValidator {

    private Float price;

    public TeaValidator(Float price) {
        this.price = price;
    }

    @Override
    public String validate(Float money) {

        if (money < price) {
            return "The tea costs " + price + ".";
        }
        return null;
    }
}

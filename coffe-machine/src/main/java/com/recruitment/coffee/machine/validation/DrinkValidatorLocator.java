package com.recruitment.coffee.machine.validation;

import com.recruitment.coffee.machine.model.DRINKS;

public interface DrinkValidatorLocator {

    DrinkValidator retrieve(DRINKS drinkEnum);
}

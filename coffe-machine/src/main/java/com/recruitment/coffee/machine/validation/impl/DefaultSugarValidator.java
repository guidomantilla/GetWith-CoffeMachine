package com.recruitment.coffee.machine.validation.impl;

import com.recruitment.coffee.machine.validation.SugarValidator;

public class DefaultSugarValidator implements SugarValidator {

    @Override
    public String validate(String drinkType, Integer sugarsNo, Boolean isExtraHot) {

        if (!(sugarsNo >= 0 && sugarsNo <= 2)) {
            return "The number of sugars should be between 0 and 2.";
        }

        String message = "You have ordered a " + drinkType;

        if (isExtraHot) {
            message += " extra hot";
        }

        if (sugarsNo > 0) {
            message += " with " + sugarsNo + " sugars (stick included)";
        } else {
            message += " with " + sugarsNo + " sugar";
        }

        return message;
    }
}

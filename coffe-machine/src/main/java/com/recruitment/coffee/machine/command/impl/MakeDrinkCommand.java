package com.recruitment.coffee.machine.command.impl;

import com.recruitment.coffee.machine.cli.Input;
import com.recruitment.coffee.machine.cli.Output;
import com.recruitment.coffee.machine.command.Command;
import com.recruitment.coffee.machine.model.DRINKS;
import com.recruitment.coffee.machine.validation.DrinkValidator;
import com.recruitment.coffee.machine.validation.DrinkValidatorLocator;
import com.recruitment.coffee.machine.validation.SugarValidator;
import org.apache.commons.lang3.EnumUtils;

import java.util.Objects;

public class MakeDrinkCommand implements Command {

    private DrinkValidatorLocator drinkValidatorLocator;
    private SugarValidator sugarValidator;


    public MakeDrinkCommand(DrinkValidatorLocator drinkValidatorLocator, SugarValidator sugarValidator) {
        this.drinkValidatorLocator = drinkValidatorLocator;
        this.sugarValidator = sugarValidator;
    }

    @Override
    public void execute(Input input, Output out) {

        String drinkType = input.getParameter("drinkType");
        if (!EnumUtils.isValidEnumIgnoreCase(DRINKS.class, drinkType)) {
            out.run("The drink type should be tea, coffee or chocolate.");
            return;
        }

        Float money = input.getParameter("money");
        Integer sugarsNo = input.getParameter("sugar");
        Boolean isExtraHot = input.getParameter("extraHot");

        DRINKS drinkEnum = EnumUtils.getEnumIgnoreCase(DRINKS.class, drinkType);
        DrinkValidator drinkValidator = drinkValidatorLocator.retrieve(drinkEnum);
        String message = drinkValidator.validate(money);

        if (!Objects.isNull(message)) {
            out.run(message);
            return;
        }

        message = sugarValidator.validate(drinkType, sugarsNo, isExtraHot);
        if (!Objects.isNull(message)) {
            out.run(message);
            return;
        }
    }
}


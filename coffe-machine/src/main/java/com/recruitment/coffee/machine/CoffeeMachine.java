package com.recruitment.coffee.machine;

import com.recruitment.coffee.machine.cli.Input;
import com.recruitment.coffee.machine.cli.Output;
import com.recruitment.coffee.machine.cli.impl.CliOutput;
import com.recruitment.coffee.machine.cli.impl.InputArguments;
import com.recruitment.coffee.machine.command.Command;
import com.recruitment.coffee.machine.command.impl.MakeDrinkCommand;
import com.recruitment.coffee.machine.validation.DrinkValidatorLocator;
import com.recruitment.coffee.machine.validation.SugarValidator;
import com.recruitment.coffee.machine.validation.impl.*;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

public class CoffeeMachine implements Callable<Void> {

    @Parameters(arity = "1", index = "0", description = "The type of the drink."
            + " (Tea, Coffee or Chocolate)")
    private String drinkType;

    @Parameters(arity = "1", index = "1", description = "The amount of money "
            + "given by the user")
    private Float money;

    @Parameters(arity = "0...1", index = "2", description = "The number of "
            + "sugars the user wants. (0, 1, 2)", defaultValue = "0")
    private Integer sugar;

    @Option(names = {"-e", "--extra-hot"}, required = false,
            description = "If the user wants to make the drink extra hot",
            defaultValue = "false")
    private Boolean extraHot;

    public static void main(String[] args) {

        CommandLine.call(new CoffeeMachine(), args);
    }

    @Override
    public Void call() {

        TeaValidator teaValidator = new TeaValidator(0.4f);
        CoffeeValidator coffeeValidator = new CoffeeValidator(0.5f);
        ChocolateValidator chocolateValidator = new ChocolateValidator(0.6f);

        DrinkValidatorLocator drinkValidatorLocator = new DefaultDrinkValidatorLocator(teaValidator, coffeeValidator, chocolateValidator);
        SugarValidator sugarValidator = new DefaultSugarValidator();

        Input input = new InputArguments(drinkType, money, sugar, extraHot);
        Output out = new CliOutput();

        Command command = new MakeDrinkCommand(drinkValidatorLocator, sugarValidator);
        command.execute(input, out);

        return null;
    }
}

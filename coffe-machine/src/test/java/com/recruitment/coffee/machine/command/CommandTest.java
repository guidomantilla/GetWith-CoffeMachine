package com.recruitment.coffee.machine.command;

import com.recruitment.coffee.machine.cli.Input;
import com.recruitment.coffee.machine.cli.Output;
import com.recruitment.coffee.machine.cli.impl.InputArguments;
import com.recruitment.coffee.machine.command.impl.MakeDrinkCommand;
import com.recruitment.coffee.machine.validation.DrinkValidatorLocator;
import com.recruitment.coffee.machine.validation.SugarValidator;
import com.recruitment.coffee.machine.validation.impl.*;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@RunWith(DataProviderRunner.class)
public class CommandTest {

    @Mock
    private Output output;

    @DataProvider
    public static Object[][] drinkCombinations() {
        return new Object[][]{
                {new InputArguments("coffee", (float) 1, 1, false), "You have ordered a coffee with 1 sugars (stick included)"},
                {new InputArguments("coffee", (float) 1, 1, true), "You have ordered a coffee extra hot with 1 sugars (stick included)"},
                {new InputArguments("coffee", (float) 1, 0, false), "You have ordered a coffee with 0 sugar"},
                {new InputArguments("coffee", (float) 0.1, 1, false), "The coffee costs 0.5."},

                {new InputArguments("tea", (float) 1, 1, false), "You have ordered a tea with 1 sugars (stick included)"},
                {new InputArguments("tea", (float) 1, 1, true), "You have ordered a tea extra hot with 1 sugars (stick included)"},
                {new InputArguments("tea", (float) 1, 0, false), "You have ordered a tea with 0 sugar"},
                {new InputArguments("tea", (float) 0.1, 1, false), "The tea costs 0.4."},

                {new InputArguments("chocolate", (float) 1, 1, false), "You have ordered a chocolate with 1 sugars (stick included)"},
                {new InputArguments("chocolate", (float) 1, 1, true), "You have ordered a chocolate extra hot with 1 sugars (stick included)"},
                {new InputArguments("chocolate", (float) 1, 0, false), "You have ordered a chocolate with 0 sugar"},
                {new InputArguments("chocolate", (float) 0.1, 1, false), "The chocolate costs 0.6."},

                {new InputArguments("foo", (float) 0.1, 1, false), "The drink type should be tea, coffee or chocolate."},
        };
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @UseDataProvider("drinkCombinations")
    public void testCoffeeMachineReturnsExpectedOutputForCoffeeCombinations(Input input, String expectedMessage) {

        TeaValidator teaValidator = new TeaValidator(0.4f);
        CoffeeValidator coffeeValidator = new CoffeeValidator(0.5f);
        ChocolateValidator chocolateValidator = new ChocolateValidator(0.6f);

        DrinkValidatorLocator drinkValidatorLocator = new DefaultDrinkValidatorLocator(teaValidator, coffeeValidator, chocolateValidator);
        SugarValidator sugarValidator = new DefaultSugarValidator();

        Command command = new MakeDrinkCommand(drinkValidatorLocator, sugarValidator);
        command.execute(input, output);

        Mockito.verify(output, Mockito.times(1)).run(expectedMessage);
    }
}
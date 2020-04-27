package com.recruitment.coffee.machine.command;

import com.recruitment.coffee.machine.cli.Input;
import com.recruitment.coffee.machine.cli.Output;

public interface Command {

    void execute(Input input, Output out);
}

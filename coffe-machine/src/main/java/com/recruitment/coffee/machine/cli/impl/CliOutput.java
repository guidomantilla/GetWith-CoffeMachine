package com.recruitment.coffee.machine.cli.impl;

import com.recruitment.coffee.machine.cli.Output;

public class CliOutput implements Output {

    @Override
    public void run(String message) {
        System.out.println(message);
    }
}

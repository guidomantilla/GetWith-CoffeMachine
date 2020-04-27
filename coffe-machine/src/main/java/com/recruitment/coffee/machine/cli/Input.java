package com.recruitment.coffee.machine.cli;

public interface Input {

    <T> T getParameter(String parameterName);
}

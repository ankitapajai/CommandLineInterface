package com.bootcoding.UserCommandApp.command.validator;

public interface CommandValidator {
    public boolean validate(String[] attributes) throws Exception;
}

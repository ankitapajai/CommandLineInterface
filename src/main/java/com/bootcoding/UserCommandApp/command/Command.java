package com.bootcoding.UserCommandApp.command;

import com.bootcoding.UserCommandApp.model.Result;

public interface Command {
    public Result execute(String[] attributes) throws Exception;
}

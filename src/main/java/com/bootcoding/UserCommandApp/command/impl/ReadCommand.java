package com.bootcoding.UserCommandApp.command.impl;

import com.bootcoding.UserCommandApp.command.Command;
import com.bootcoding.UserCommandApp.command.validator.CommandValidator;
import com.bootcoding.UserCommandApp.model.Result;
import com.bootcoding.UserCommandApp.model.User;
import com.bootcoding.UserCommandApp.store.InMemoryStore;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ReadCommand implements Command, CommandValidator {

    @Override
    public Result execute(String[] attributes) throws Exception {
        if(validate(attributes)){
            switch(attributes[1]){
                case "-n":
                    return readByName(attributes[2]);
                case "-i":
                    return readById(attributes[2]);
                case "-all":
                    return readByAll();
            }
        }
        return Result.builder().message("Invalid read command arguments").build();
    }

    private Result readByAll(){
        return Result.builder().message("SUCCESS").users(InMemoryStore.users).build();
    }

    private Result readById(String id){
        for(User user : InMemoryStore.users){
            if(user.getId().equals(id)){
                return Result.builder().message("SUCCESS").users(Arrays.asList(user)).build();
            }
        }
        return Result.builder().message("FAILED: User Id(" + id +") doesn't exists!").build();
    }

    private Result readByName(String name){
        for(User user : InMemoryStore.users){
            if(user.getName().equals(name)){
                return Result.builder().message("SUCCESS").users(Arrays.asList(user)).build();
            }
        }
        return Result.builder().message("FAILED: User Name (" + name +") doesn't exists!").build();
    }

    @Override
    public boolean validate(String[] attributes) throws Exception {
        if(attributes.length < 2 && attributes.length > 3){
            throw new Exception("Please provide all attributes: " + "For ex: \ncreate -n \"Ankita\" -p 12345 -a " +
                    "\"Nagpur nagar\" -e \"iamcoder@gmail.com\"");
        }

        if(!attributes[0].equals("read")){
            throw new Exception("Action must be read! ");
        }

        String attrName = attributes[1];
        return validateAttributes(attrName);
    }

    private boolean validateAttributes(String attrName){
        switch (attrName){
            case "-n":
                return true;
            case "-i":
                return true;
            case "-all":
                return true;
            default:
                return false;
        }
    }
}

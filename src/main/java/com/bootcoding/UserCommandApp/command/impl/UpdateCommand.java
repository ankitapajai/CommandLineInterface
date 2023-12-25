package com.bootcoding.UserCommandApp.command.impl;

import com.bootcoding.UserCommandApp.command.Command;
import com.bootcoding.UserCommandApp.command.validator.CommandValidator;
import com.bootcoding.UserCommandApp.model.Result;
import com.bootcoding.UserCommandApp.model.User;
import com.bootcoding.UserCommandApp.store.InMemoryStore;

public class UpdateCommand implements Command, CommandValidator {
    public Result execute(String[] attributes) {
        // write logic for update user
        // identify which field has to update of that user
        String id = attributes[2];
        String field = attributes[3];
        String newValue = attributes[4];
        User user = getUser(id);
        switch (field){
            case "phone":
                updatePhone(user, Long.valueOf(newValue));
                break;
            case "emailId":
                updateEmail(user, newValue);
                break;
            case "address":
                updateAddress(user, newValue);
                break;
            case "name":
                updateName(user, newValue);
        }
        return Result.builder().message("Updated successfully").build();
    }

    @Override
    public boolean validate(String[] attributes) throws Exception{

        // Getting Requirement
        // Understand use cases (scenarios) Positive and Negative

        if (attributes.length != 5) {
            throw new Exception("Please provide required attributes to update user ");
        }

        if (!attributes[0].equals("update")) {
            throw new Exception("Action must be update!");
        }

        if(!attributes[1].equals("-i")){
            throw new Exception("Invalid arguments, please provide -i ");
        }

        String id = attributes[2];
        String field = attributes[3];
        String newValue = attributes[4];

        // does that ID exists in the collection
        User user = getUser(id);
        if(user == null){
            throw new Exception("User with id " + id + " doesn't exists!");
        }

        return true;
    }

    private void updateAddress(User user, String newValue) {
        user.setAddress(newValue);
    }

    private void updateEmail(User user, String newValue) {
        user.setMailId(newValue);
    }

    private void updatePhone(User user, long newValue) {
        user.setPhone(newValue);
    }

    private User getUser(String id) {
        for (User user : InMemoryStore.users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }


    private void updateName(User user, String newValue) {
        user.setName(newValue);
    }

}

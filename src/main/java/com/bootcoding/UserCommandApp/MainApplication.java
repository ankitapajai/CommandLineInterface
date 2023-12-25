package com.bootcoding.UserCommandApp;

import com.bootcoding.UserCommandApp.command.Command;
import com.bootcoding.UserCommandApp.command.impl.CreateCommand;
import com.bootcoding.UserCommandApp.command.impl.ExitCommand;
import com.bootcoding.UserCommandApp.command.impl.ReadCommand;
import com.bootcoding.UserCommandApp.command.impl.UpdateCommand;
import com.bootcoding.UserCommandApp.model.Result;

import java.util.ArrayList;
import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {


//        var a =23487;
//        var b="ANKITA";
//        var array = new ArrayList<>();
//        var main = new MainApplication();
//        array.add(56);
//        System.out.println(array);
            while (true) {

                Scanner sc = new Scanner(System.in);

                System.out.println("======================= Welcome to Command Utility =================");
                System.out.println("To create User, command should be like ::: create -n name -p phone -a address -e emailId");
                System.out.println("=======================---------------=================");


                System.out.println("You are free to enter any command!");

                String commandStr = sc.nextLine();

                String[] commandWithAttrs = commandStr.split(" ");

                Command command = findCommand(commandWithAttrs[0]);
                if(null == command || command instanceof ExitCommand){
                    break;
                }
                try {
                    Result result = command.execute(commandWithAttrs);
                    System.out.println(result);
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

        private static Command findCommand(String cmd) {
            switch (cmd){
                case "create":
                    Command command = new CreateCommand();
                    return command;
                case "read":
                    Command command1 = new ReadCommand();
                    return command1;
                case "update":
                    Command command2 = new UpdateCommand();
                    return command2;
                  default:
                      return null;
            }
      }
}

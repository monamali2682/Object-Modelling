package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IUserService;

public class CreateUserCommand implements Icommand{
IUserService userService;

    public CreateUserCommand(IUserService userService) {
        this.userService=userService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String name = tokens.get(1);
        userService.createUser(name); 
    }
    
}

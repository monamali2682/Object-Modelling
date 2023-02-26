package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IUserService;

public class ModifyPlaylistDeleteSongCommand implements Icommand{
    IUserService userService;

    public ModifyPlaylistDeleteSongCommand(IUserService userService) {
        this.userService=userService;
    }

    @Override
    public void invoke(List<String> tokens) {
        // TODO Auto-generated method stub
        
    }
    
}

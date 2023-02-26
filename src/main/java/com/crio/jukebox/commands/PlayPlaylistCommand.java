package com.crio.jukebox.commands;

import java.util.List;
//import com.crio.jukebox.commands.Icommand;
import com.crio.jukebox.services.IUserService;

public class PlayPlaylistCommand implements Icommand {
    IUserService userService;

    public PlayPlaylistCommand(IUserService userService) {
        this.userService=userService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long userid = Long.parseLong(tokens.get(1));
        Long playlistid= Long.parseLong(tokens.get(2));
        userService.play(userid, playlistid);  
    }
    
}

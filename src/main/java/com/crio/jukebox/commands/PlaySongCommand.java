package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IUserService;

public class PlaySongCommand implements Icommand{
    IUserService userService;

    public PlaySongCommand(IUserService userService) {
        this.userService=userService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long userId = Long.parseLong(tokens.get(1));
        String commandType= tokens.get(2);
        if(commandType.equals("BACK")){
            userService.playPreviousSong(userId);
        }
        else if(commandType.equals("NEXT")){
            userService.playNextSong(userId);
        }
        else{
            Long songId = Long.parseLong(commandType);
            userService.playThisSong(userId, songId);
        }  
    }
    
}

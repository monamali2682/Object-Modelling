package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;

public class DeletePlaylistCommand implements Icommand {
    IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService=playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String userid = tokens.get(1);
        String playlistid= tokens.get(2);
        playlistService.deletePlaylist(userid, playlistid);
    }
    
}

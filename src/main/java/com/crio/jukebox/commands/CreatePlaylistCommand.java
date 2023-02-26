package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements Icommand{
    IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService=playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String userid= tokens.get(1);
        String playlistName = tokens.get(2);
        int n= tokens.size();
        long[] songIds= new long[n-3];
        int idx=0;
        for(int i=3;i<n;i++){
            Long songId = Long.parseLong(tokens.get(i));
            songIds[idx]=songId;
            idx++;
        }
        playlistService.createPlaylist(userid, playlistName, songIds);
    }
    
}

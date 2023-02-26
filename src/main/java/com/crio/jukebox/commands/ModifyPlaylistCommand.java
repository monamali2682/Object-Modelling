package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;
//import com.crio.jukebox.services.IUserService;

public class ModifyPlaylistCommand implements Icommand{
    IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService=playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String modifyType = tokens.get(1);
        String userid=tokens.get(2);
        String playlistid=tokens.get(3);

        int n= tokens.size();
        long[] songIds= new long[n-4];
        int idx=0;
        for(int i=4;i<n;i++){
            Long songId = Long.parseLong(tokens.get(i));
            songIds[idx]=songId;
            idx++;
        }
        if(modifyType.equals("ADD-SONG")){
            playlistService.addSongToPlaylist(userid, playlistid, songIds);
        }
        else{
            playlistService.deleteSongFromPlaylist(userid, playlistid, songIds);
        }   
    }
    
}

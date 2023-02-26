package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.ISongService;
//import com.crio.jukebox.services.SongService;

public class LoadDataCommand implements Icommand{
    ISongService songService;

    public LoadDataCommand(ISongService songService) {
        this.songService=songService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String filename = tokens.get(1);
        songService.loadSongs(filename);
    }
    
}

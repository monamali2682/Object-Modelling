package com.crio.jukebox.services;

public interface ISongService {
    public void loadSongs(String filename);
    public void createSong(String[] inputSong);
}

package com.crio.jukebox.services;

public interface IUserService {
    public void play(Long userId, long Playlist_id);
    public void playPreviousSong(Long userId);
    public void playNextSong(Long userId);
    public void playThisSong(Long userId,Long songId);
    public void createUser(String name);
}

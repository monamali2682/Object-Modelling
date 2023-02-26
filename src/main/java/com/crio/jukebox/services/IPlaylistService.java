package com.crio.jukebox.services;

public interface IPlaylistService {
    public void createPlaylist(String userid, String playlistName, long[] songIds);
    public void deletePlaylist(String userid, String playlistid);
    public void addSongToPlaylist(String userid, String playlistid,long[] songIds);
    public void deleteSongFromPlaylist(String userid, String playlistid,long[] songIds);
}

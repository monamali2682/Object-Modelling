package com.crio.jukebox.services;

import java.util.LinkedHashSet;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
//import com.crio.jukebox.repositories.UserrRepository;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
// import com.crio.jukebox.repositories.PlaylistRepository;
// import com.crio.jukebox.repositories.SongRepository;

public class PlaylistService implements IPlaylistService{
    IUserRepository userRepository;
    IPlaylistRepository playlistRepository;
    ISongRepository songRepository;

    public PlaylistService(IPlaylistRepository playlistRepository,IUserRepository userRepository,ISongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository=userRepository;
        this.songRepository=songRepository;
    }
    
    // function for command -> CREATE-PLAYLIST { USER_ID } { PLAYLIST_NAME } { List of Song IDs }
    public void createPlaylist(String userid, String playlistName, long[] songIds){
        Long userID = Long.parseLong(userid);
        User user = userRepository.findById(userID).orElseThrow( ()-> new RuntimeException("User not found"));
        LinkedHashSet<Long> songs= new LinkedHashSet<>();
        for(long id :songIds){
            Song song = songRepository.findById(id).orElseThrow(()-> new RuntimeException("Some Requested Songs Not Available. Please try again."));
            songs.add(id);
        }
        Playlist playlist = new Playlist(userID, playlistName, songs);
        Playlist p = playlistRepository.save(playlist);
        System.out.println(p);
    }

    // function for command -> DELETE-PLAYLIST { USER_ID } { Playlist-ID }
    public void deletePlaylist(String userid, String playlistid){
        Long userID = Long.parseLong(userid);
        User user = userRepository.findById(userID).orElseThrow( ()-> new RuntimeException("User not found"));
        Playlist playlist = playlistRepository.findById(Long.parseLong(playlistid)).orElseThrow( ()-> new RuntimeException("Playlist not found"));
        playlistRepository.delete(playlist);
        System.out.println("Delete Successful");
    }

    //MODIFY-PLAYLIST ADD-SONG { USER_ID } {Playlist-ID } { List of Song IDs }
    public void addSongToPlaylist(String userid, String playlistid,long[] songIds){
        Long userID = Long.parseLong(userid);
        User user = userRepository.findById(userID).orElseThrow( ()-> new RuntimeException("User not found"));
        Playlist playlist = playlistRepository.findById(Long.parseLong(playlistid)).orElseThrow( ()-> new RuntimeException("Playlist not found"));
        for(long id :songIds){
            Song song = songRepository.findById(id).orElseThrow(()-> new RuntimeException("Some Requested Songs Not Available. Please try again."));
            playlist.addSong(id);
        }
        Playlist p = playlistRepository.save(playlist);
        p.printInfo();
    }

    //for command MODIFY-PLAYLIST DELETE-SONG 1 1 3 4
    public void deleteSongFromPlaylist(String userid, String playlistid,long[] songIds){
        Long userID = Long.parseLong(userid);
        User user = userRepository.findById(userID).orElseThrow( ()-> new RuntimeException("User not found"));
        Playlist playlist = playlistRepository.findById(Long.parseLong(playlistid)).orElseThrow( ()-> new RuntimeException("Playlist not found"));
        for(long id :songIds){
            Boolean b = playlist.getSongids().contains(id);
            if(!b) throw new RuntimeException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            playlist.removeSong(id);
        }
        Playlist p = playlistRepository.save(playlist);
        p.printInfo();
    }
    
}

package com.crio.jukebox.services;


import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.entities.IdleState;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserrRepository;

public class UserService implements IUserService {
    IUserRepository userRepository;
    IPlaylistRepository playlistRepository;
    ISongRepository songRepository;
    
    public UserService(IUserRepository userRepository, IPlaylistRepository playlistRepository,
            ISongRepository songRepository) {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    /* Command - PLAY-PLAYLIST  { USER_ID } { Playlist-ID }
     * Load playlist and Start playing the playlist. The output will be the first song of the playlist.
     */ 
    public void play(Long userId, long Playlist_id){
        User user = userRepository.findById(userId).orElseThrow(() ->new RuntimeException("User not found!"));
        Playlist playlist = playlistRepository.findById(Playlist_id).orElseThrow(() ->new RuntimeException("Playlist not found!"));
        
        /// error message if playlist is empty
        if(playlist.getSongids().size()==0){
            System.out.println("Playlist is Empty");
        }else{
            List<Song> songplaylist= new ArrayList<>();
            for(Long id:playlist.getSongids()){
                Song song = songRepository.findById(id).orElseThrow(() ->new RuntimeException("Song not found!"));
                songplaylist.add(song);
            }
            user.setCurrPlaylist(songplaylist);
            user.setCurrIndex(0);
            user.setCurrState(new IdleState(user));
            user.play();
            userRepository.save(user);
        }  
    }

    /*
     * Command - PLAY-SONG { USER_ID }  BACK
     * Switch to play a previous song in the active playlist.  */
    public void playPreviousSong(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() ->new RuntimeException("User not found!"));
        user.prev();
        userRepository.save(user); 
    }

    /* function for -  PLAY-SONG  { USER_ID } NEXT */
    public void playNextSong(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() ->new RuntimeException("User not found!"));
        user.next();
        userRepository.save(user); 
    }

    /* function for - Switch to the preferred song in the playlist.
    PLAY-SONG { USER_ID }  { Song ID } */
    public void playThisSong(Long userId,Long songId){
        User user = userRepository.findById(userId).orElseThrow(() ->new RuntimeException("User not found!"));
        Song song = songRepository.findById(songId).orElseThrow(() ->new RuntimeException("Song not found!"));
        Boolean b = user.getCurrPlaylist().contains(song);
        if(!b) {
            System.out.println("Given song id is not a part of the active playlist");
        }
        else{
            int index = user.getCurrPlaylist().indexOf(song);
            user.playThisSong(index);
            userRepository.save(user); 
        }   
    }
    /*
     * Command Create user {name}
     */
    public void createUser(String name){
        User u = new User(name);
        User user = userRepository.save(u);
        System.out.println(user);
    }
}

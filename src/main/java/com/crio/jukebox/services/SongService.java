package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.SongRepository;

public class SongService implements ISongService{
    ISongRepository songRepository;

    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void loadSongs(String filename){
        String splitBy = ",";
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (true) {
                String line = reader.readLine();
                if (line == null){
                    break;
                }
                String[] inputSong = line.split(splitBy);
                createSong(inputSong);
            }
            System.out.println("Songs Loaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createSong(String[] inputSong) {
        String[] artists =inputSong[inputSong.length-1].split("#");
        Song song = new Song(inputSong[0],inputSong[1],inputSong[2],inputSong[3],artists);
        Song s = songRepository.save(song);
    }
}

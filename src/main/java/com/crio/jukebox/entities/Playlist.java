package com.crio.jukebox.entities;

import java.util.LinkedHashSet;

public class Playlist {
    
    long id;
    long userId;
    String name;
    LinkedHashSet<Long> songids;
    
    public Playlist(long userId, String name, LinkedHashSet<Long> songids) {
        this.userId = userId;
        this.name = name;
        this.songids = songids;
    }

    public Playlist(long id, long userId, String name, LinkedHashSet<Long> songids) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.songids = songids;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedHashSet<Long> getSongids() {
        return songids;
    }

    public void setSongids(LinkedHashSet<Long> songids) {
        this.songids = songids;
    }

    @Override
    public String toString() {
        return "Playlist ID" + " - " + id ;
    }

    /*
     *  Playlist ID - 1
        Playlist Name - MY_PLAYLIST_1
        Song IDs - 1 2 3 4 5 6 7
     */
    public void printInfo() {
        String songIdsToString = "";
        for(long id :this.songids){
            songIdsToString+=id;
            songIdsToString+=" ";
        }
        String trimmmedstring= songIdsToString.trim();
        System.out.println("Playlist ID" + " - " + id);
        System.out.println("Playlist Name" + " - " + name);
        System.out.println("Song IDs" + " - " + trimmmedstring);
    }

    public void addSong(long id) {
        this.songids.add(id);
    }

    public void removeSong(long id2) {
        this.songids.remove(id2);
    }
    
}

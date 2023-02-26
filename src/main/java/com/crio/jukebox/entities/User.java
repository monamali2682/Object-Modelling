package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    long id;
    String name;
    List<Song> currPlaylist;
    int currIndex;
    // state of song player i.e state of jukebox
    IPlayerState currState; 
    
    public User(String name) {
        this.id=0L;
        this.name = name;
        this.currPlaylist = new ArrayList<>();
        this.currIndex = 0;
        this.currState = new IdleState(this);
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
        this.currPlaylist = new ArrayList<>();
        this.currIndex = 0;
        this.currState = new IdleState(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getCurrPlaylist() {
        return currPlaylist;
    }

    public void setCurrPlaylist(List<Song> currPlaylist) {
        this.currPlaylist = currPlaylist;
    }

    public int getCurrIndex() {
        return currIndex;
    }

    public void setCurrIndex(int currIndex) {
        this.currIndex = currIndex;
    }

    public IPlayerState getCurrState() {
        return currState;
    }

    public void setCurrState(IPlayerState currState) {
        this.currState = currState;
    }

    @Override
    public String toString() {
        return id + " "+ name;
    }

    public void play(){
        this.currState.play();
    }
    public void prev(){
        this.currState.prev();
    }
    public void next(){
        this.currState.next();
    }
    public void stop(){
        this.currState.stop();
    }

    public void incrementSongIndex() {
        int size = this.currPlaylist.size();
        if(this.currIndex==size-1){
            this.currIndex=0;
        }else{
            this.currIndex++;
        }
    }

    public void decrementSongIndex() {
        int size = this.currPlaylist.size();
        if(this.currIndex==0){
            this.currIndex=size-1;
        }else{
            this.currIndex--;
        }
    }

    public void playThisSong(int index) {
        this.currState.playThis(index);
    }
}

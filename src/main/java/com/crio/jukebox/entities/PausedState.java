package com.crio.jukebox.entities;

public class PausedState implements IPlayerState {
    User user;
    
    public PausedState(User user) {
        this.user = user;
    }

    @Override
    public void play() {
        int songindex = this.user.getCurrIndex();
        Song song = this.user.currPlaylist.get(songindex);
        System.out.println(song+ " is playing!");
        this.user.setCurrState(new PlayingState(this.user));   
    }

    @Override
    public void next() {
        this.user.incrementSongIndex();
        int songindex = this.user.currIndex;
        Song song = this.user.currPlaylist.get(songindex);
        System.out.println(song+ " is playing!");
        this.user.setCurrState(new PlayingState(this.user)); 
    }

    @Override
    public void prev() {
        this.user.decrementSongIndex();
        int songindex = this.user.currIndex;
        Song song = this.user.currPlaylist.get(songindex);
        System.out.println(song+ " is playing!");
        this.user.setCurrState(new PlayingState(this.user));
    }

    @Override
    public void stop() {
        int songindex = this.user.currIndex;
        Song song = this.user.currPlaylist.get(songindex);
        System.out.println(song+ " is stopped!");
        this.user.setCurrState(new IdleState(this.user)); 
    }

    @Override
    public void playThis(int index) {
        this.user.setCurrIndex(index);
        Song song = user.getCurrPlaylist().get(user.getCurrIndex());
        System.out.println("Current Song Playing");
        System.out.println("Song - "+ song.getName());
        System.out.println("Album - "+song.getAlbum());
        System.out.println("Artists - "+song.getArtistsnames());
        this.user.setCurrState(new PlayingState(this.user)); 
    }
}

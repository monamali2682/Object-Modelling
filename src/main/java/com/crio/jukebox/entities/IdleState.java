package com.crio.jukebox.entities;

public class IdleState implements IPlayerState {
    User user;

    public IdleState(User user) {
        this.user=user;
    }

    public void play(){
        Song song = user.getCurrPlaylist().get(user.getCurrIndex());
        System.out.println("Current Song Playing");
        System.out.println("Song - "+ song.getName());
        System.out.println("Album - "+song.getAlbum());
        System.out.println("Artists - "+song.getArtistsnames());
        this.user.setCurrState(new PlayingState(this.user));
    }

    @Override
    public void next() {
        System.out.println("Can not play next song from Idle state");
        
    }

    @Override
    public void prev() {
        System.out.println("Can not play previous song from Idle state");
        
    }

    @Override
    public void stop() {
        System.out.println("Can not stop song from Idle state");
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

package com.crio.jukebox.entities;

public interface IPlayerState {
    public void play();
    public void next();
    public void prev();
    public void stop();
    public void playThis(int index);
}

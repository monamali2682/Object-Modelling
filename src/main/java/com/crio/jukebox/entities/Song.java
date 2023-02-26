package com.crio.jukebox.entities;

//import java.util.Arrays;

public class Song {
    Long id;
    String name;
    String genre;
    String album;
    String albumArtist;
    String[] artists;

    public Song(Long id, String name, String genre, String album, String albumArtist,String[] artists) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.album = album;
        this.albumArtist=albumArtist;
        this.artists = artists;
    }

    public Song(String name, String genre, String album, String albumArtist, String[] artists) {
        this.name = name;
        this.genre = genre;
        this.album = album;
        this.albumArtist=albumArtist;
        this.artists = artists;
    }
    
    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtists(String[] artists) {
        this.artists = artists;
    }
    public String[] getArtists(){
        return artists;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        /*
            Song - Give Life Back To Music
            Album - Random Access Memories
            Artists - Daft Punk,Nile Rodgers
         */
        return "Song - "+ this.name + "\n" +
        "Album - "+ this.album + "\n" +
        "Artists - "+ this.getArtistsnames();
    }

    public String getArtistsnames() {
        StringBuilder sb = new StringBuilder();
        for(String s: artists){
            sb.append(s);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    
    
}

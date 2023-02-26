package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository{
    HashMap<Long, Song> map;
    Long autoincrement;
    
    public SongRepository() {
        this.map = new HashMap<Long, Song>();
        this.autoincrement = 0L;
    }

    @Override
    public Song save(Song entity) {
        if(entity.getId()==null){
            autoincrement++;
            Song song = new Song(autoincrement ,entity.getName(), entity.getGenre(),entity.getAlbum(),entity.getAlbumArtist(), entity.getArtists());
            map.put(autoincrement, song);
            return song;
        }
        map.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Song> findAll() {
        return map.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return map.containsKey(id);
    }

    @Override
    public void delete(Song entity) {
        map.remove(entity.getId());    
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id); 
    }

    @Override
    public long count() {
        return map.size();
    }
    public Optional<Song> findByName(String name) {
        return map.values().stream().filter(u -> u.getName().equals(name)).findFirst();
    }
}

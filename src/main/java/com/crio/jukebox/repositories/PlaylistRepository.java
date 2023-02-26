package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;

public class PlaylistRepository implements IPlaylistRepository{

    HashMap<Long, Playlist> map;
    long autoincrement;
    
    public PlaylistRepository() {
        this.map = new HashMap<Long, Playlist>();
        this.autoincrement = 0L;
    }

    @Override
    public Playlist save(Playlist entity) {
        if(entity.getId()==0L){
            autoincrement++;
            Playlist playlist = new Playlist(autoincrement, entity.getUserId() ,entity.getName(), entity.getSongids());
            map.put(autoincrement, playlist);
            return playlist;
        }
        map.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Playlist> findAll() {
        return map.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return map.containsKey(id);
    }

    @Override
    public void delete(Playlist entity) {
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
    public Optional<Playlist> findByName(String name) {
        return map.values().stream().filter(u -> u.getName().equals(name)).findFirst();
    }
    
}

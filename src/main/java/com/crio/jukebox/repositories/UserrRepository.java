package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.User;


public class UserrRepository implements IUserRepository {
    HashMap<Long, User> map;
    long autoincrement=0L;

    public UserrRepository(HashMap<Long, User> map) {
        this.map = map;
        this.autoincrement = map.size();
    }

    public UserrRepository() {
        this.map = new HashMap<Long, User>();
    }
    
    public User save(User u){
        if(u.getId()==0L){
            autoincrement++;
            User user = new User(autoincrement ,u.getName());
            map.put(autoincrement, user);
            return user;
        }
        map.put(u.getId(), u);
        return u;
    }


    @Override
    public void delete(User entity) {
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

    public Optional<User> findByName(String name) {
     return map.values().stream().filter(u -> u.getName().equals(name)).findFirst();
    }

    @Override
    public List<User> findAll() {
        return map.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return map.containsKey(id);
    }

}

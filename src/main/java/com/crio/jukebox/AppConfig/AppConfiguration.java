package com.crio.jukebox.AppConfig;

import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.UserrRepository;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.UserService;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.CommandRegistry;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlaylistAddSongCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.ModifyPlaylistDeleteSongCommand;
import com.crio.jukebox.commands.PLaySongBackCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.commands.PlaySongNextCommand;
import com.crio.jukebox.commands.PlaySongWithIdCommand;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.PlaylistService;
import com.crio.jukebox.services.SongService;

public class AppConfiguration {
    private static AppConfiguration INSTANCE= new AppConfiguration();

    private AppConfiguration(){
    }

    public static AppConfiguration getINSTANCE() {
        return INSTANCE;
    }

    CommandRegistry commandRegistry = new CommandRegistry();

    // repositories--> DAO layers
    ISongRepository songRepository = new SongRepository();
    IPlaylistRepository playlistRepository = new PlaylistRepository();
    IUserRepository userRepository = new UserrRepository();

    // Services --> service layer
    ISongService songService = new SongService(songRepository);
    IPlaylistService playlistService = new PlaylistService(playlistRepository, userRepository, songRepository);
    IUserService userService = new UserService(userRepository, playlistRepository, songRepository);
    
    // Commands
    LoadDataCommand loadDataCommand = new LoadDataCommand(songService);
    CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playlistService); 
    PlayPlaylistCommand playPlaylistCommand = new PlayPlaylistCommand(userService);
    ModifyPlaylistCommand modifyPlaylistCommand = new ModifyPlaylistCommand(playlistService);
    // ModifyPlaylistAddSongCommand modifyPlaylistAddSongCommand = new ModifyPlaylistAddSongCommand(userService);
    // ModifyPlaylistDeleteSongCommand modifyPlaylistDeleteSongCommand = new ModifyPlaylistDeleteSongCommand(userService);
    PlaySongCommand playSongCommand = new PlaySongCommand(userService);
    // PlaySongWithIdCommand playSongWithIdCommand = new PlaySongWithIdCommand(userService);
    // PlaySongNextCommand playSongNextCommand = new PlaySongNextCommand(userService);
    // PLaySongBackCommand pLaySongBackCommand = new PLaySongBackCommand(userService);

    public void registerCommands(){
        commandRegistry.register("LOAD-DATA", loadDataCommand);
        commandRegistry.register("CREATE-USER", createUserCommand);
        commandRegistry.register("CREATE-PLAYLIST", createPlaylistCommand);
        commandRegistry.register("DELETE-PLAYLIST", deletePlaylistCommand);
        commandRegistry.register("PLAY-PLAYLIST", playPlaylistCommand);
        // commandRegistry.register("MODIFY-PLAYLIST ADD-SONG", modifyPlaylistAddSongCommand);
        // commandRegistry.register("MODIFY-PLAYLIST DELETE-SONG", modifyPlaylistDeleteSongCommand);
        commandRegistry.register("MODIFY-PLAYLIST", modifyPlaylistCommand);
        // commandRegistry.register("PLAY-SONG", playSongWithIdCommand);
        // commandRegistry.register("PLAY-SONG-NEXT", playSongNextCommand);
        // commandRegistry.register("PLAY-SONG-BACK", pLaySongBackCommand);
        commandRegistry.register("PLAY-SONG", playSongCommand);
    }

    public CommandRegistry getCommandRegistry(){
        registerCommands();
        return commandRegistry;
    }

}

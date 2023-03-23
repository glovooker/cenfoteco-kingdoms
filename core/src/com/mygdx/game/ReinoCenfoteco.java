package com.mygdx.game;

import Screens.Menu;
import Screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ReinoCenfoteco extends Game {
    public SpriteBatch batch;
    public static AssetManager manager;

    private PlayScreen playScreen;

    private Menu menuScreen;

    private static ReinoCenfoteco kingdomInstance;

    private ReinoCenfoteco(){}


    @Override
    public void create() {
        batch = new SpriteBatch();
        manager = new AssetManager();
        manager.load("audio/MenuMusic.mp3", Music.class);
        manager.finishLoading();
        setScreen(new Menu());
    }

     public static ReinoCenfoteco getInstance(){
        if(kingdomInstance == null){
            kingdomInstance = new ReinoCenfoteco();
        }

        return kingdomInstance;
     }

    public void setPlayScreen(Music music){
        this.playScreen = new PlayScreen(music);
        setScreen(playScreen);
    }


    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
        batch.dispose();
    }

}

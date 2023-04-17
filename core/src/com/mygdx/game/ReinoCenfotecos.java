package com.mygdx.game;

import View.Screens.Menu;
import View.Screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ReinoCenfotecos extends Game {
    public SpriteBatch batch;
    public static AssetManager manager;

    private PlayScreen playScreen;

    private Menu menuScreen;

    private static ReinoCenfotecos kingdomInstance;

    private ReinoCenfotecos(){}


    @Override
    public void create() {
        batch = new SpriteBatch();
        manager = new AssetManager();
        Gdx.input.setInputProcessor(new InputMultiplexer());
        manager.load("audio/MenuMusic.mp3", Music.class);
        manager.finishLoading();
        setScreen(new Menu());
    }

     public static ReinoCenfotecos getInstance(){
        if(kingdomInstance == null){
            kingdomInstance = new ReinoCenfotecos();
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

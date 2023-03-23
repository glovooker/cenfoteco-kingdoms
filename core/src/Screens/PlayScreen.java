package Screens;

import View.ChestView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ReinoCenfoteco;

public class PlayScreen implements Screen {
    private ReinoCenfoteco game;

    private OrthographicCamera gameCam;

    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;

    private OrthogonalTiledMapRenderer renderer;

    private World world;

    private Box2DDebugRenderer b2dr;


    private Stage stage;


    private ChestView chest;


    public PlayScreen(Music music){
        music.stop();
        this.game = ReinoCenfoteco.getInstance();
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(800, 820, gameCam);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("DungeonTest2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
       gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/2, 0);
       world = new World(new Vector2(0, -10), true);
       b2dr = new Box2DDebugRenderer();
       this.stage = new Stage(gamePort);
       this.stage.getRoot().setX(600);
       this.stage.getRoot().setY(350);
       chest = new ChestView(stage);


    }


    public void update(){
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        b2dr.render(world, gameCam.combined);
        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();
        game.batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
    }

    public World getWorld(){
        return world;
    }

    public TiledMap getMap(){
        return map;
    }
}

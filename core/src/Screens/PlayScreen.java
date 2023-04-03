package Screens;

import BL.GameController;
import View.BoardView;
import View.ChestView;
import View.FiguresView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
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

    private BoardView boardView;


    private Stage chestStage;

    private Stage boardStage;

    private TextureAtlas atlasBoard;

    private TextureAtlas atlasFigures;

    private TextureAtlas castles;

    private ChestView chest;
    private Stage figuresView;



    private final GameController gameController = GameController.getInstance();


    public PlayScreen(Music music){
        music.stop();
        atlasBoard = new TextureAtlas("boardAtlas.atlas");
        atlasFigures = new TextureAtlas("figuresAtlas.atlas");
        castles = new TextureAtlas("Castles.atlas");
        this.game = ReinoCenfoteco.getInstance();
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(1600, 1600, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("World.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/2, 0);
        world = new World(new Vector2(0, -10), true);

        b2dr = new Box2DDebugRenderer();
        this.boardView = new BoardView(this,gamePort);
        this.boardStage = this.boardView.getStageBoard();

        this.boardStage.getRoot().setX(250);
        this.boardStage.getRoot().setY(400);

        this.figuresView = new FiguresView(this.gamePort, this);
        this.figuresView.getRoot().setX(0);
        this.figuresView.getRoot().setY(400);




        this.chestStage = new Stage(gamePort);
        this.chestStage.getRoot().setX(0);
        this.chestStage.getRoot().setY(0);

        chest = new ChestView(chestStage);

        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(chestStage);
        inputMultiplexer.addProcessor(figuresView);
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

        boardStage.act(Gdx.graphics.getDeltaTime());

        boardStage.draw();
        chestStage.act(Gdx.graphics.getDeltaTime());
        chestStage.draw();

       figuresView.act(Gdx.graphics.getDeltaTime());
       figuresView.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        chestStage.draw();
        boardStage.draw();
        figuresView.draw();
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
        chestStage.dispose();
        boardStage.dispose();
        figuresView.dispose();
    }

    public World getWorld(){
        return world;
    }

    public TiledMap getMap(){
        return map;
    }

    public TextureAtlas getBoardAtlas(){
        return this.atlasBoard;
    }

    public TextureAtlas getFiguresAtlas(){
        return this.atlasFigures;
    }

    public TextureAtlas getCastlesAtlas(){
        return this.castles;
    }
}
